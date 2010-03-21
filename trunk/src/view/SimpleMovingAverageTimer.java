package src.view;

/**
 * A timer that reports rates as averages over an user-specified number of the
 * most recent events.
 */
public class SimpleMovingAverageTimer extends Timer {
	/**
	 * The default sample size.
	 */
	public final static int DEFAULT_SAMPLE_SIZE = 20;

	private long prevTime;
	private int marks;
	private int sampleSize;
	private long[] sample;
	private int pointer;
	private long sum;

	/**
	 * Prepares a simple moving average timer with a default sample size.
	 */
	public SimpleMovingAverageTimer() {
		this(DEFAULT_SAMPLE_SIZE);
	}

	/**
	 * Prepares a simple moving average timer with the specified sample size.
	 * 
	 * @param sampleSize
	 *            the number of marks over which to average for rate-reporting
	 */
	public SimpleMovingAverageTimer(int sampleSize) {
		this.sampleSize = sampleSize;
		sample = new long[sampleSize];
		pointer = 0;
	}

	/**
	 * Inserts the latest mark in the elapsed time calculation, removing stale
	 * data to keep the sample size no larger than was specified in the
	 * constructor.
	 */
	protected void doMark() {
		if(marks < sampleSize)
			++marks;
		long nanos = System.nanoTime();
		sum -= sample[pointer];
		sample[pointer] = nanos - prevTime;
		sum += sample[pointer];
		pointer = (pointer + 1) % sampleSize;
		prevTime = nanos;
	}

	/**
	 * Remembers the time at which this method was called with nanosecond
	 * precision for duration calculations.
	 */
	protected void doStart() {
		prevTime = System.nanoTime();
	}

	/**
	 * Does nothing, as no extra work is needed to stop this timer.
	 */
	protected void doStop() {
	}

	/**
	 * Gives the number of counted marks.
	 */
	protected long marksElapsed() {
		return marks;
	}

	/**
	 * Clears the collected samples without affecting the running state of this
	 * timer.
	 */
	public void reset() {
		marks = 0;
		sum = 0;
		prevTime = System.nanoTime();
		if(sample != null)
			for(int i = 0; i != sampleSize; ++i)
				sample[i] = 0;
	}

	/**
	 * Gives a sum over the durations of the counted marks.
	 */
	protected long nanosecondsElapsed() {
		return sum;
	}
}
