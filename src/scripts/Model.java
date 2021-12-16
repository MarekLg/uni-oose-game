package scripts;

public class Model {

	private ModelState state = ModelState.IDLE;

	private final IsometricImage idle;
	private final Animation walking;

	public Model(IsometricImage idle, Animation walking) {
		this.idle = idle;
		this.walking = walking;
	}

	public void setState(ModelState state) {
		if (this.state != state)
			walking.resetCounter();

		this.state = state;
	}

	public String getImageForDirection(Direction direction) {
		final IsometricImage image;

		switch (state) {
			case IDLE:
				image = idle;
				break;
			case WALKING:
				image = walking.getFrame();
				break;
			default:
				throw new IllegalStateException();
		}

		return image.getImageForDirection(direction);
	}

	public static Model createModel(String prefix, int frameTime) {
		final var idleImages = new String[8];

		for (var i = 0; i < 8; i++)
			idleImages[i] = String.format("%s_%s_Idle0.png", prefix, i);

		final var walkingAnimation = new IsometricImage[10];
		final var images = new String[8];

		for (var frame = 0; frame < 10; frame++) {
			for (var i = 0; i < 8; i++)
				images[i] = String.format("%s_%s_Run%s.png", prefix, i, frame);

			walkingAnimation[frame] = new IsometricImage(images);
		}

		return new Model(new IsometricImage(idleImages), new Animation(walkingAnimation, frameTime));
	}
}
