package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import src.control.FileHandler;
import src.control.KeyMap;
import src.control.interfaces.DisplayableKeyMap;
import src.model.Model;
import src.model.Player;
import src.model.commands.Command;
import src.model.commands.NoArgsCommand;
import src.model.commands.RallyPointCommand;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.Unit;
import src.model.instances.rallypoints.RallyPointManager;
import src.model.instances.units.SpecificUnitManager;
import src.model.instances.units.StarterUnit;
import src.model.interfaces.GameTile;
import src.util.handv1.HandFactoryImp;
import src.view.View;

public class TestDisplayableControls {
	public static void main( String[] args )
	{
        Model m = new Model( new HandFactoryImp() );
		final Player p = m.human();
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
			}
			
		});
		StarterUnit starter = new StarterUnit( p, 0, p.startingLocation() );
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
        
        //load controls from file into KeyMap object.
        KeyMap controls = new KeyMap();
        BufferedReader r;
		try{
		r = new BufferedReader(new FileReader("controller config files/controls.txt"));
		FileHandler.readFile(controls,r);
		}catch (IOException ex) {
        ex.printStackTrace();
		}	
		System.out.println(controls);
		
		view.displayControls(controls);
	}
}
