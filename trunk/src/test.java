package src;

import src.model.Model;
import src.model.Player;
import src.model.commands.NoArgsCommand;
import src.model.commands.RallyPointCommand;
import src.model.instances.Unit;
import src.model.instances.rallypoints.RallyPointManager;
import src.model.instances.units.SpecificUnitManager;
import src.util.handv1.HandFactoryImp;

public class test {
	public static void main( String[] args )
	{
		Player p = new Model( new HandFactoryImp() ).p;
		System.out.println( "cmdMakeRanged" );
		p.executeCommand( new NoArgsCommand( "cmdMakeRanged", null, 0 ) );
		System.out.println( "cmdArcherAP1" );
		p.executeCommand( new NoArgsCommand( "cmdResRangedAtkPow", null, 0 ) );
		System.out.println( "cmdMakeRanged" );
		p.executeCommand( new NoArgsCommand( "cmdMakeRanged", null, 0 ) );
		System.out.println( "cmdMakeRallyPoint" );
		
		Unit u = SpecificUnitManager.lastUnit;
		
		p.executeCommand( new NoArgsCommand( "cmdMakeRallyPoint", null, 0 ) );
		System.out.println( "cmdRallyPoint" );
		RallyPointCommand rpc = new RallyPointCommand( "cmdRallyPoint", null, 0 );
		rpc.setRallyPoint( RallyPointManager.lastRP );
		u.executeCommand( rpc );
		System.out.println( "cmdDecommission" );
		u.executeCommand( new NoArgsCommand( "cmdDecommission", null, 0 ) );
	}
}
