package src.util;


public class SimpleMovingAverageTimer extends Timer {

	public final static int DEFAULT_SAMPLE_SIZE = 20;

	private long prevTime;
	private int marks;
	private int sampleSize;
	private long[] sample;
	private int pointer;
	private long sum;


	public SimpleMovingAverageTimer() {
		this(DEFAULT_SAMPLE_SIZE);
	}

	public SimpleMovingAverageTimer(int sampleSize) {
		this.sampleSize = sampleSize;
		sample = new long[sampleSize];
		pointer = 0;
	}

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

	protected void doStart() {
		prevTime = System.nanoTime();
	}

	protected void doStop() {
	}

	protected long marksElapsed() {
		return marks;
	}

	public void reset() {
		marks = 0;
		sum = 0;
		prevTime = System.nanoTime();
		if(sample != null)
			for(int i = 0; i != sampleSize; ++i)
				sample[i] = 0;
	}

	protected long nanosecondsElapsed() {
		return sum;
	}
}
