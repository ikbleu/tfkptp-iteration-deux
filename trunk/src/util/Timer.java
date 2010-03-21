package src.util;

public abstract class Timer {
	private boolean running = false;

	public Timer() {
		reset();
	}


	public final void start() {
		running = true;
		doStart();
	}


	protected abstract void doStart();


	public final void stop() {
		running = false;
		doStop();
	}


	protected abstract void doStop();

	public abstract void reset();

	public final void mark() {
		if(!running)
			throw new IllegalStateException("Called mark() on stopped Timer!");
		doMark();
	}
	
	protected abstract void doMark();

	protected abstract long nanosecondsElapsed();
	
	protected abstract long marksElapsed();

	public final double marksPerSecond() {
		return 1000000000d * ((double) marksElapsed()) / nanosecondsElapsed();
	}

	public final double secondsPerMark() {
		return ((double) nanosecondsElapsed()) / marksElapsed();
	}
}
