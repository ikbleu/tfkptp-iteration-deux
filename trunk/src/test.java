package src;

import javax.tools.JavaFileManager.Location;

import src.model.Model;
import src.model.Player;
import src.model.commands.Command;
import src.model.commands.NoArgsCommand;
import src.model.commands.RallyPointCommand;
import src.model.enums.Direction;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.Unit;
import src.model.instances.rallypoints.RallyPointManager;
import src.model.instances.units.SpecificUnitManager;
import src.model.instances.units.StarterUnit;
import src.model.interfaces.GameTile;
import src.util.handv1.HandFactoryImp;
import src.view.View;

public class test {
	public static void main( String[] args )
	{
        Model m = new Model( new HandFactoryImp() );
		final Player p = m.human();
		StarterUnit starter = new StarterUnit( p, 0, p.startingLocation() );
		p.addInstanceExistenceListener( new InstanceExistenceListener() {

			@Override
			public void delInstance(Instance i) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void newInstance(Instance i) {
				// TODO Auto-generated method stub
				System.out.println( "new Instance: " + i.token() );
				GameTile g = i.location();
				System.out.printf( "Location: %d %d %d\n",g.getX(), g.getY(), g.getZ() );
				i.moveTo( i.location().getNeighbor( Direction.S ) );
				i.moveTo( i.location().getNeighbor( Direction.S ) );
				p.vMan().update();
				p.vMan().update();
				i.moveTo( i.location().getNeighbor( Direction.N ) );
				i.moveTo( i.location().getNeighbor( Direction.N ) );
				p.vMan().update();
				
			}
			
		});
		System.out.println( "cmdMakeColonist" );
		starter.executeCommand( new NoArgsCommand( "cmdMakeColonist", starter, 0, true ) );
		/*System.out.println( "cmdArcherAP1" );
		p.executeCommand( new NoArgsCommand( "cmdResRangedAtkPow", null, 0 ) );
		System.out.println( "cmdMakeRanged" );
		p.executeCommand( new NoArgsCommand( "cmdMakeRanged", null, 0 ) );
		System.out.println( "cmdMakeRallyPoint" );
		
		Unit u = SpecificUnitManager.lastUnit;
		
		p.vMan().update();
		p.vMan().update();
		
		p.executeCommand( new NoArgsCommand( "cmdMakeRallyPoint", null, 0 ) );
		System.out.println( "cmdRallyPoint" );
		RallyPointCommand rpc = new RallyPointCommand( "cmdRallyPoint", null, 0 );
		rpc.setRallyPoint( RallyPointManager.lastRP );
		u.executeCommand( rpc );
		System.out.println( "cmdDecommission" );
		u.executeCommand( new NoArgsCommand( "cmdDecommission", null, 0 ) );*/
		

		p.vMan().update();
		p.vMan().update();
        View view = new View(p.getSakuraMap());
	}
}
