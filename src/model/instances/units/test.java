package src.model.instances.units;

import src.model.Model;
import src.model.Player;
import src.model.commands.NoArgsCommand;

public class test {
	public static void main( String[] args )
	{
		Player p = new Model().p;
		System.out.println( "cmdMakeRanged" );
		p.executeCommand( new NoArgsCommand( "cmdMakeRanged", null, 0 ) );
		System.out.println( "cmdArcherAP1" );
		p.executeCommand( new NoArgsCommand( "cmdResRangedAtkPow", null, 0 ) );
		System.out.println( "cmdMakeRanged" );
		p.executeCommand( new NoArgsCommand( "cmdMakeRanged", null, 0 ) );
		System.out.println( "cmdDecommission" );
		SpecificUnitManager.lastUnit.executeCommand( new NoArgsCommand( "cmdDecommission", null, 0 ) );
	}
}
