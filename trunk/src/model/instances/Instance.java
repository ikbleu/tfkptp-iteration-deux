package src.model.instances;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import src.model.AoEManager;
import src.model.HasPlayerManager;
import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandFactory;
import src.model.commands.CommandListener;
import src.model.commands.CommandSender;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayer;
import src.model.interfaces.HasPlayerVisitor;
import src.model.interfaces.HealthListener;
import src.model.interfaces.InstanceVisitor;
import src.model.interfaces.MovementListener;
import src.model.interfaces.StatsListener;
import src.model.interfaces.ViewListener;
import src.model.interfaces.ViewVisitor;
import src.model.interfaces.vInstance;
import src.model.interfaces.LocatableVisitor;
import src.model.interfaces.vInstanceVisitor;
import src.util.Hand;

public abstract class Instance extends Locatable implements vInstance, CommandSender, Device, HasPlayer
{
	public Instance( Player p, int id, GameTile g )
	{
		super( g );
		
		this.id = id;
		player = p;
		commandHand = p.handFactory().make( Device.class );
		
		AoEManager.instance().registerLocation( this );
		HasPlayerManager.getInstance().add( location(), this );
		
		for ( InstanceExistenceListener il : globalListeners )
			il.newInstance( this );
	}
	private Hand< Device > commandHand;
	
	private Player player;
	final public Player player()
	{
		return player;
	}
	
	private int id;
	final public int id()
	{
		return id;
	}
	
	final public String context()
	{
		return "Instance";
	}
	
	final public String meaning()
	{
		return token() + id();
	}
	
	final public void direct(KeyEventInterpreterBuilder builder)
	{
		builder.devices( commandHand.spawnLens() );
	}
	
	private Map< String, Integer > stats = new HashMap< String, Integer >();
	public void stats( Map< String, Integer > m )
	{
		m.putAll( stats );
	}
	
	final public int getStat( String s )
	{
		Integer val = stats.get( s );
		return ( val == null ? 0 : val );
	}
	
	final public int health()
	{
		return stats.get( "statHealth" );
	}
	
	final public void takeDamage( int delta )
	{
		modifyStat( "statHealth", -delta );
	}

        final public void heal( int delta )
        {
            int curHealth = getStat("statHealth");
            int maxHealth = getStat("statMaxHealth");

            int healing = Math.min(maxHealth - curHealth, delta);
            modifyStat( "statHealth", delta );
        }
	
	final protected void setStat( String s, int val )
	{
		stats.put( s, val );
		if ( stats.equals( "statHealth" ) )
		{
			for ( HealthListener hl : healthListeners )
				hl.healthChanged( this );
			for ( ViewListener vl : viewListeners )
				vl.healthChanged( this );
			if ( health() <= 0 )
				destroy();
		}
		else
		{
			for ( StatsListener sl : statsListeners )
				sl.statsChanged( this );
			for ( ViewListener vl : viewListeners )
				vl.statsChanged( this );
			
			if ( s.equals( "statInfluenceRadius" ) )
				setInfluenceRadius( val );
		}
	}
	
	protected void doDestruction()
	{
		for ( InstanceExistenceListener il : ieListeners )
			il.delInstance( this );
		for ( InstanceExistenceListener il : globalListeners )
				il.delInstance( this );
		AoEManager.instance().unregisterLocation( this );
		HasPlayerManager.getInstance().remove( location(), this );
	}
	
	final public void modifyStat( String s, int delta )
	{
		setStat( s, getStat( s ) + delta );
	}
	
	private List< StatsListener > statsListeners = new CopyOnWriteArrayList< StatsListener >();
	final public void addStatsListener( StatsListener cl )
	{
		statsListeners.add( cl );
	}
	final public void removeStatsListener( StatsListener cl )
	{
		statsListeners.remove( cl );
	}
	
	private List< HealthListener > healthListeners = new CopyOnWriteArrayList< HealthListener >();
	final public void addHealthListener( HealthListener cl )
	{
		healthListeners.add( cl );
	}
	final public void removeHealthListener( HealthListener cl )
	{
		healthListeners.remove( cl );
	}
	
	private List< ViewListener > viewListeners = new CopyOnWriteArrayList< ViewListener >();
	final public void addViewListener( ViewListener cl )
	{
		viewListeners.add( cl );
	}
	final public void removeViewListener( ViewListener cl )
	{
		viewListeners.remove( cl );
	}
	
	private List< InstanceExistenceListener > ieListeners = new CopyOnWriteArrayList< InstanceExistenceListener >();
	final public void addInstanceExistenceListener( InstanceExistenceListener cl )
	{
		ieListeners.add( cl );
		cl.newInstance( this );
	}
	final public void removeInstanceExistenceListener( InstanceExistenceListener cl )
	{
		ieListeners.remove( cl );
	}
	
	private static List< InstanceExistenceListener > globalListeners = new CopyOnWriteArrayList< InstanceExistenceListener >();
	public static void addGlobalInstanceExistenceListener( InstanceExistenceListener cl )
	{
		globalListeners.add( cl );
	}
	public static void removeGlobalInstanceExistenceListener( InstanceExistenceListener cl )
	{
		globalListeners.remove( cl );
	}
	
	private Map< String, List< CommandListener > > commandListeners = new HashMap< String, List< CommandListener > >();
	final public void addCommandListener( String token, CommandListener cl )
	{
		if ( ! commandListeners.containsKey( token ) )
			commandListeners.put( token, new CopyOnWriteArrayList< CommandListener >() );
		commandListeners.get( token ).add( cl );
	}
	final public void removeCommandListener( String token, CommandListener cl )
	{
		if ( commandListeners.containsKey( token ) )
			commandListeners.get( token ).remove( cl );
	}
	
	final public void executeCommand( Command c )
	{
		List< CommandListener > ls = commandListeners.get( c.token() );
		if ( ls != null )
			for ( CommandListener cl : ls )
				cl.commandOccurred( c );
		player.executeCommand( c );
	}
	
	final public void accept( LocatableVisitor lv )
	{
		lv.visitInstance( this );
	}
	abstract public void accept( vInstanceVisitor v );
	abstract public void accept( InstanceVisitor v );
	
	private List< MovementListener > moveListeners = new CopyOnWriteArrayList< MovementListener >();
	final public void addMovementListener( MovementListener ml )
	{
		moveListeners.add( ml );
	}
	final public void removeMovementListener( MovementListener ml )
	{
		moveListeners.remove( ml );
	}
	
	public void addSelectableCommand( CommandFactory cmd )
	{
		cmd.setInstance( this );
		commandHand.add( cmd );
	}
	
	public void removeSelectableCommand( CommandFactory cmd )
	{
		commandHand.remove( cmd );
	}
	
	abstract public String token();
	
	public void moveTo( GameTile loc )
	{
		try { HasPlayerManager.getInstance().move(loc, location(), this); }
		catch ( Exception e ) {}
		GameTile prev = location();
		setLocation( loc );
		for ( MovementListener ml : moveListeners )
			ml.locationChanged( this, prev );
		for ( ViewListener vl : viewListeners )
			vl.locationChanged( this, prev );
	}

	public List<GameTile> getVisibleTiles() {
		int visRad = getStat("statVisibilityRadius");
		System.out.println("Visibility Radius " + visRad);
                return location().getTilesAround(visRad);
	}

        public abstract void receiveWorkers(WorkerGroup wg, int numWorkers);

        public abstract void sendWorkers(WorkerGroup wg, int numWorkers);
	
	public Player getPlayer()
	{
		return player;
	}
	
	public boolean hasSamePlayer(HasPlayer hs)
	{
		return player == hs.getPlayer();
	}
	
	public abstract void accept(HasPlayerVisitor hpv);


	@Override
	final public String comparable() {
		// TODO Auto-generated method stub
		return meaning();
	}
	
	public void addCommandToQueue( Command c )
	{
		if ( c.isInstant() )
			executeCommand( c );
		else
			queue.add( c );
	}
	
	final public String getCurrentAction()
	{
		if ( queue.size() == 0 )
			return "cmdNone";
		return queue.peek().token();
	}
	private LinkedList< Command > queue = new LinkedList< Command >();

        public abstract Map<String, Integer> getUpkeep();

        public abstract void sentUpkeep(Map<String, Integer> resources);

	@Override
	public void accept(ViewVisitor v) {
		// TODO Auto-generated method stub
		v.visitInstance( this );
	}
}
