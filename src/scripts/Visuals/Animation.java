package scripts.Visuals;

public class Animation {
	private final IsometricImage[] frames;
	private final int frameTime;
	private Runnable onFinished;
	private int counter;
	private int index;

	public Animation(IsometricImage[] frames, int frameTime) {
		this.frames = frames;
		this.frameTime = frameTime;
		counter = 0;
		index = 0;
	}

	public void setOnFinished(Runnable onFinished) {
		this.onFinished = onFinished;
	}

	public void resetCounter() {
		counter = 0;
	}

	public IsometricImage getFrame() {
		counter++;

		if (counter >= frameTime) {
			index++;

			if (index >= frames.length) {
				if (onFinished != null)
					onFinished.run();

				index = 0;
			}

			resetCounter();
		}

		return frames[index];
	}

	protected int getFrameIndex() {
		return index;
	}
}
