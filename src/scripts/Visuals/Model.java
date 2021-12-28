package scripts.Visuals;

import scripts.Direction;

public class Model {

	private enum State {
		IDLE, WALKING, PICKUP
	}

	private State state = State.IDLE;

	private final IsometricImage idle;
	private final Animation walking;
	private final CallbackAnimation pickup;

	public Model(IsometricImage idle, Animation walking) {
		this(idle, walking, null);
	}

	public Model(IsometricImage idle, Animation walking, CallbackAnimation pickup) {
		this.idle = idle;
		this.walking = walking;
		this.pickup = pickup;

		if (pickup != null)
			pickup.setOnFinished(() -> state = State.IDLE);
	}

	public boolean isPickingUp() {
		return state == State.PICKUP;
	}

	public void setIdle() {
		state = State.IDLE;
	}

	public void setWalking() {
		if (state == State.WALKING)
			return;

		walking.resetCounter();

		state = State.WALKING;
	}

	public void setPickup(Runnable onPickup) {
		if (pickup == null || state == State.PICKUP)
			return;

		pickup.setCallback(onPickup);
		pickup.resetCounter();
		state = State.PICKUP;
	}

	public String getImageForDirection(Direction direction) {
		final var image = getImage();

		return image.getImageForDirection(direction);
	}

	private IsometricImage getImage() {
		switch (state) {
			case IDLE:
				return idle;
			case WALKING:
				return walking.getFrame();
			case PICKUP:
				if (pickup == null)
					throw new IllegalStateException("State is pickup, but pickup animation is null.");
				return pickup.getFrame();
			default:
				throw new IllegalStateException();
		}
	}
}
