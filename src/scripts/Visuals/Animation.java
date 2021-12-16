package scripts.Visuals;

public class Animation {
	private final IsometricImage[] frames;
	private final int frameTime;
	private int counter;
	private int index;

	public Animation(IsometricImage[] frames, int frameTime) {
		if (frameTime <= 0 || frames.length == 0)
			throw new IllegalArgumentException();

		this.frames = frames;
		this.frameTime = frameTime;
		counter = 0;
		index = 0;
	}

	public void resetCounter() {
		counter = 0;
	}

	public IsometricImage getFrame() {
		counter++;

		if (counter >= frameTime) {
			index++;
			index %= frames.length;

			resetCounter();
		}

		return frames[index];
	}
}
