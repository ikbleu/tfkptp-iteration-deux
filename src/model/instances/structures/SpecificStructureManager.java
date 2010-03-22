package src.model.instances.structures;

import java.util.Map;

import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandListener;
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
	public SpecificStructureManager(GeneralStructureManager m, StructureFactory f, Player p, Hand< Device > h,
			Map< String, Integer > baseStats, final Map< String, Integer > deltaStats, Map< String, Integer > resourceCost,
			String researchID  ) {
		manager = m;
		factory = f;
		hand = h;
		player = p;
		this.deltaStats = deltaStats;
		this.researchID = researchID;
		this.resourceCost = resourceCost;
		
		for ( Map.Entry< String, Integer > e : baseStats.entrySet() )
			factory.modDefaultStat( e.getKey(), e.getValue() );
		factory.modDefaultStat( "statHealth", baseStats.get( "statMaxHealth" ) );
		
		p.addCommandListener( "cmdRes" + researchID + "VisRadius", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statVisibilityRadius", deltaStats.get( "statVisibilityRadius" ) );
			}
		});
		
		p.addCommandListener( "cmdRes" + researchID + "AtkPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statAttackPower", deltaStats.get( "statAttackPower" ));
			}
		});
		
		p.addCommandListener( "cmdRes" + researchID + "DefPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statDefensePower", deltaStats.get( "statDefensePower" ));
			}
		});
		
		p.addCommandListener( "cmdRes" + researchID + "Armor", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statArmor", deltaStats.get( "statArmor" ));
			}
		});
		
		p.addCommandListener( "cmdRes" + researchID + "Health", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statMaxHealth", deltaStats.get( "statMaxHealth" ));
			}
		});
		
		p.addCommandListener( "cmdRes" + researchID + "Efficiency", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statEfficiency", deltaStats.get( "statEfficiency" ));
			}
		});
	}
	private GeneralStructureManager manager; 
	private StructureFactory factory;
	private Hand< Device > hand;
	private Player player;
	private Map< String, Integer > deltaStats;
	private String researchID;
	private Map< String, Integer > resourceCost;
	
	public boolean canMakeStructure( GameTile g )
	{
		// TODO: check resources
		return canMakeSpecificStructure( g ) && manager.canMakeStructure( g );
	}
	
	public static Structure lastStructure = null; // TODO: remove
	
	// precondition: canMakeUnit() is true
	final public Structure makeStructure( GameTile g )
	{
		final Structure s = factory.makeInstance( g );


		player.addCommandListener( "cmdRes" + researchID + "VisRadius", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					s.modifyStat( "statVisibilityRadius", deltaStats.get( "statVisibilityRadius" ) );
			}
		});
		
		player.addCommandListener( "cmdRes" + researchID + "AtkPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					s.modifyStat( "statAttackPower", deltaStats.get( "statAttackPower" ));
			}
		});
		
		player.addCommandListener( "cmdRes" + researchID + "DefPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					s.modifyStat( "statDefensePower", deltaStats.get( "statDefensePower" ));
			}
		});
		
		player.addCommandListener( "cmdRes" + researchID + "Armor", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					s.modifyStat( "statArmor", deltaStats.get( "statArmor" ));
			}
		});
		
		player.addCommandListener( "cmdRes" + researchID + "Health", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
				{
					s.modifyStat( "statMaxHealth", deltaStats.get( "statMaxHealth" ));
					s.modifyStat( "statHealth", deltaStats.get( "statMaxHealth" ));
				}
			}
		});
		
		player.addCommandListener( "cmdRes" + researchID + "Efficiency", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					s.modifyStat( "statEfficiency", deltaStats.get( "statEfficiency" ));
			}
		});

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
