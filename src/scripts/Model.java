package scripts;

public class Model {

	private ModelState state = ModelState.WALKING;

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
}
