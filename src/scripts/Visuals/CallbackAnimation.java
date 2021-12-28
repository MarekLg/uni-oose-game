package scripts.Visuals;

public class CallbackAnimation extends Animation {
	private final int callbackFrame;
	private Runnable callback;

	public CallbackAnimation(IsometricImage[] frames, int frameTime, int callbackFrame) {
		super(frames, frameTime);

		if (callbackFrame < 0 || callbackFrame >= frames.length)
			throw new IllegalArgumentException("callbackFrame must be >= 0 and < frames.length");

		this.callbackFrame = callbackFrame;
	}

	@Override
	public IsometricImage getFrame() {
		if (getFrameIndex() == callbackFrame && callback != null)
			callback.run();

		return super.getFrame();
	}

	public void setCallback(Runnable callback) {
		this.callback = callback;
	}
}
