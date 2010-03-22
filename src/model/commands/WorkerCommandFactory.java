/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.model.commands;

import src.model.Player;
import src.model.instances.Instance;
import src.model.interfaces.InstanceVisitor;
import src.model.instances.Unit;
import src.model.instances.Structure;
import src.model.instances.RallyPoint;

/**
 *
 * @author Christopher Dudley
 */
public class WorkerCommandFactory extends CommandFactory
{
    public int arg;

    public WorkerCommandFactory( Player p, String token, int numTicks)
    {
        super(p, token, numTicks);
        arg = 0;
    }

    public WorkerCommandFactory( Player p, String token, int numTicks, boolean isInstant )
    {
	super( p, token, numTicks, isInstant );
        arg = 0;
    }

    protected void doSetInstance(final Instance i)
    {
        String[] meanings = {"0", "1", "2", "3", "4", "5", "6", "7","8","9"};

        for(final String meaning : meanings)
        {
            addArgument(new Argument(meaning) {
                public void execute()
                {
                    arg = arg * 10 + Integer.parseInt(meaning);
                }
            });
        }

        addArgument(new Argument("finish") {
            public void execute()
            {
                WorkerCommand wCom = makeCommand(i);
                wCom.setNumWorkers(arg);
                i.accept(new WGSetter(wCom));
                i.addCommandToQueue(wCom);
                arg = 0;
            }
        });

        addArgument(new Argument("cancel")
        {
            public void execute()
            {
                arg = 0;
            }
        });
    }

    public WorkerCommand makeCommand( Instance i )
    {
        return new WorkerCommand(token(), i, numTicks(), isInstant());
    }

    public String comparable()
    {
        return meaning();
    }

    public String context()
    {
        return "intAccumulation";
    }

    private class WGSetter implements InstanceVisitor
    {
        private WorkerCommand wCom;

        public WGSetter(WorkerCommand wCom)
        {
            this.wCom = wCom;
        }

        public void visitUnit(Unit u) {}

        public void visitStructure(Structure s)
        {
            wCom.setWorkerGroup(s.getStaff());
        }

        public void visitRallyPoint(RallyPoint rp)
        {
            wCom.setWorkerGroup(rp.getWorkers());
        }
    }
}
