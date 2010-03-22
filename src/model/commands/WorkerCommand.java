package src.model.commands;

import src.model.instances.Instance;
import src.model.instances.WorkerGroup;

public class WorkerCommand extends Command {
	public WorkerCommand(String token, Instance i, int numTicks) {
		super(token, i, numTicks);
	}
	public WorkerCommand(String token, Instance i, int numTicks, boolean isInstant) {
		super(token, i, numTicks, isInstant);
	}

	public void accept(CommandVisitor c) {
		c.visitWorkerCommand(this);
	}

	public WorkerGroup getWorkerGroup()
	{
		return wg;
	}

        public int getNumWorkers()
        {
            return numWorkers;
        }

	public void setWorkerGroup( WorkerGroup wg )
	{
		this.wg = wg;
	}

        public void setNumWorkers(int numWorkers)
        {
            this.numWorkers = numWorkers;
        }

	private WorkerGroup wg;
        private int numWorkers;
}