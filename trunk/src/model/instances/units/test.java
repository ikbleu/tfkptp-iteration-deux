package src.model.instances.units;

import src.model.Model;
import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandVisitor;
import src.model.instances.Instance;
import src.model.interfaces.GameTile;

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

			@Override
			public int numTicks() {
				// TODO Auto-generated method stub
				return 0;
			}
			
		});
	}
}
