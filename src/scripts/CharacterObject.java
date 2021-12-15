package scripts;

import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.Vertex;

public class CharacterObject<I> extends ScaledImageObject<I> {
	private IsometricImage image;
	private Direction direction;

	public CharacterObject(IsometricImage image, double scale) {
		super(image.getImageForDirection(Direction.NE), scale);
		this.image = image;
		direction = Direction.NE;
	}

	public CharacterObject(IsometricImage image, Vertex position, double scale) {
		super(image.getImageForDirection(Direction.NE), position, scale);
		this.image = image;
		direction = Direction.NE;
	}

	@Override
	public void setVelocity(Vertex v) {
		super.setVelocity(v);

		final var vector = Vector.fromVertex(v);

		if (!vector.isApproxZero()) {
			final var lastIntValue = direction.intValue();

			direction = Direction.fromVector(vector);

			if (direction.intValue() != lastIntValue)
				setImageFileName(image.getImageForDirection(direction));
		}
	}
}
