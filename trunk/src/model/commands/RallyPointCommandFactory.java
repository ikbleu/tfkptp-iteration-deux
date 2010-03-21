package src.model.commands;

import java.util.LinkedList;
import java.util.List;

import src.model.Player;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.RallyPoint;
import src.model.interfaces.InstanceAdapter;

public class RallyPointCommandFactory extends CommandFactory {
	private static List< RallyPoint > rallyPoints = new LinkedList< RallyPoint >();
	public RallyPointCommandFactory( Player p, String token, int numTicks ) { 
		super( p, token, numTicks );
	}
	
	protected void doSetInstance( final Instance i )
	{
		// add command arguments
		for ( final RallyPoint rp : rallyPoints )
			addArgument( new Argument( rp.token() ) {
				public void execute()
				{
					RallyPointCommand d = makeCommand( i );
					d.setRallyPoint( rp );
					i.executeCommand( d );
				}

				@Override
				public <S> Comparable<S> comparable() {
					// TODO Auto-generated method stub
					return null;
				}
			});
	}
	
	public RallyPointCommand makeCommand(Instance i)
	{
		return new RallyPointCommand( token(), i, numTicks() );
	}
	
	{
		Instance.addGlobalInstanceExistenceListener( new InstanceExistenceListener() {
			public void delInstance(Instance i) {
				i.accept( new InstanceAdapter() {
					public void visitRallyPoint( RallyPoint rp )
					{
						rallyPoints.remove( rp );
					}
				});
			}
			public void newInstance(Instance i) {
				i.accept( new InstanceAdapter() {
					public void visitRallyPoint( RallyPoint rp )
					{
						rallyPoints.add( rp );
					}
				});
			}
		});
	}

	@Override
	public <S> Comparable<S> comparable() {
		// TODO Auto-generated method stub
		return null;
	}
}