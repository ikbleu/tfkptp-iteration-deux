package src.view;

/**
 * Framework for timing events occurring at regular intervals. Allows for the
 * implementation of different timing strategies.
 */
public abstract class Timer {
	private boolean running = false;

	/**
	 * Prepares a new timer.
	 */
	public Timer() {
		reset();
	}

	/**
	 * Sets the running flag to true and starts this timer.
	 */
	public final void start() {
		running = true;
		doStart();
	}

	/**
	 * Implementation-specific starting strategy.
	 */
	protected abstract void doStart();

	/**
	 * Sets the running flag to false and stops this timer.
	 */
	public final void stop() {
		running = false;
		doStop();
	}

	/**
	 * Implementation-specific stopping strategy.
	 */
	protected abstract void doStop();

	/**
	 * Resets the marks and time counted by this timer without affecting its
	 * running state.
	 */
	public abstract void reset();

	/**
	 * Triggers one occurrence of the events counted by this timer.
	 */
	public final void mark() {
		if(!running)
			throw new IllegalStateException("Called mark() on stopped Timer!");
		doMark();
	}
	
	/**
	 * Implementation-specific marking strategy.
	 */
	protected abstract void doMark();

	/**
	 * Retrieves the number of elapsed nanoseconds counted by this timer.
	 */
	protected abstract long nanosecondsElapsed();

	/**
	 * Retrieves the number of elapsed marks counted by this timer.
	 */
	protected abstract long marksElapsed();

	/**
	 * Computes the average number of marks occurring in a second.
	 */
	public final double marksPerSecond() {
		return 1000000000d * ((double) marksElapsed()) / nanosecondsElapsed();
	}

	/**
	 * Computes the average number of seconds elapsed between marks.
	 */
	public final double secondsPerMark() {
		return ((double) nanosecondsElapsed()) / marksElapsed();
	}
}
