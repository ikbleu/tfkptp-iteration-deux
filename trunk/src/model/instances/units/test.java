package src.model.instances.units;

import src.model.Model;
import src.model.Player;
import src.model.TokenTerrainWalkability;
import src.model.enums.Direction;
import src.model.instances.Command;
import src.model.instances.CommandVisitor;
import src.model.instances.Instance;
import src.model.interfaces.GameTile;
import src.model.interfaces.Token;

public class test {
	public static void main( String[] args )
	{
		Player p = new Model().p;
		p.executeCommand( new Command() {

			@Override
			public void accept(CommandVisitor c) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Instance instance() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public GameTile location() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String token() {
				// TODO Auto-generated method stub
				return "cmdMakeRanged";
			}
			
		});
	}
}
