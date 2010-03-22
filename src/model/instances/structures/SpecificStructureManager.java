package src.model.instances.structures;

import src.model.Player;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.Structure;
import src.model.interfaces.GameTile;
import src.model.interfaces.ViewVisitor;
import src.model.interfaces.vType;
import src.util.Hand;

abstract public class SpecificStructureManager implements InstanceExistenceListener, vType {
	public SpecificStructureManager(GeneralStructureManager m, StructureFactory f, Player p, Hand< Device > h ) {
		manager = m;
		factory = f;
		hand = h;
		player = p;
	}
	private GeneralStructureManager manager; 
	private StructureFactory factory;
	private Hand< Device > hand;
	private Player player;
	
	public boolean canMakeUnit( GameTile g )
	{
		return canMakeSpecificStructure( g ) && manager.canMakeStructure( g );
	}
	
	public static Structure lastStructure = null; // TODO: remove
	
	// precondition: canMakeUnit() is true
	final public Structure makeStructure( GameTile g )
	{
		final Structure s = factory.makeInstance( g );

		s.addInstanceExistenceListener( this );
		s.addInstanceExistenceListener( factory );
		s.addInstanceExistenceListener( manager );
		lastStructure = s; // TODO: remove
		return s;
	}
	
	protected StructureFactory structureFactory()
	{
		return factory;
	}
	
	abstract protected boolean canMakeSpecificStructure( GameTile g );
	
	public void delInstance(Instance i) {
		hand.remove( i );
		doDelInstance( i );
	}
	abstract protected void doDelInstance( Instance i );
	
	public void newInstance(Instance i) {
		hand.add( i );
		doNewInstance( i );
	}
	abstract protected void doNewInstance( Instance i );
	
	final public String context()
	{
		return "Type";
	}
	
	final public void direct(KeyEventInterpreterBuilder builder)
	{
		builder.devices( hand.spawnLens() );
	}
	
	@Override
	public void accept(ViewVisitor v) {
		// TODO Auto-generated method stub
		v.visitType( this );
	}
	
	public String token()
	{
		return meaning();
	}
}
