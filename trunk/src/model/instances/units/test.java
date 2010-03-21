package src.model.instances.units;

import src.model.Model;
import src.model.Player;
import src.model.commands.NoArgsCommand;
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
		System.out.println( "cmdDecommission" );
		SpecificUnitManager.lastUnit.executeCommand( new NoArgsCommand( "cmdDecommission", null, 0 ) );
	}
}
