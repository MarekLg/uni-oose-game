package scripts.Characters;

import name.panitz.game.framework.GraphicsTool;
import scripts.Direction;
import scripts.Globals;
import scripts.Vector;
import scripts.Visuals.Animation;
import scripts.Visuals.CallbackAnimation;
import scripts.Visuals.IsometricImage;
import scripts.Visuals.Model;
import scripts.Visuals.ScaledImageObject;

public class CharacterObject<I> extends ScaledImageObject<I> {
	protected final Model model;
	private Direction direction;

	public CharacterObject(Model model, double scale) {
		super(model.getImageForDirection(Direction.NE), scale);
		this.model = model;
		direction = Direction.NE;
	}

	@Override
	public void move() {
		if (model.isPickingUp()) {
			setVelocity(Vector.zero);
		} else {
			final var v = ensureInBounds(Vector.from(getVelocity()));
			setVelocity(v);

			if (v.magnitudeSqr() > 0.001) {
				direction = Direction.fromVector(v);
				model.setWalking();
			} else
				model.setIdle();
		}

		super.move();
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		setImageFileName(model.getImageForDirection(direction));

		super.paintTo(g);
	}

	public boolean touches(CharacterObject<I> other) {
		final var center = getCenter();
		final var otherCenter = other.getCenter();

		return Math.abs(center.x - otherCenter.x) < 16.0
				&& Math.abs(center.y - otherCenter.y) < 16.0;
	}

	public Vector getCenter() {
		return new Vector(Globals.isometricSpriteWidth * 0.5, Globals.isometricSpriteHeight * 0.875)
				.scale(getScale())
				.add(getPos());
	}

	public void setCenter(Vector center) {
		getPos().moveTo(center
				.add(new Vector(-Globals.isometricSpriteWidth * 0.5, -Globals.isometricSpriteHeight * 0.875)
						.scale(getScale())));
	}

	private Vector ensureInBounds(Vector v) {
		final var paddingX = 12;
		final var paddingY = 4;
		final var pos = v.add(getCenter());

		final var tileWidth = Globals.isometricBaseWidth * Globals.mapScale;
		final var tileHeight = Globals.isometricBaseHeight * Globals.mapScale;

		if (pos.x - paddingX < tileWidth && v.x < 0
				|| pos.x + paddingX > Globals.mapWidth() - tileWidth && v.x > 0) {
			v.x = 0;
		}

		if (pos.y - paddingY < tileHeight && v.y < 0
				|| pos.y + paddingY > Globals.mapHeight() - tileHeight && v.y > 0) {
			v.y = 0;
		}

		return v;
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

	public static Model createModel(String prefix, int walkingFrameTime, int pickupFrameTime, int pickupCallbackFrame) {
		final var idleImages = new String[8];

		for (var i = 0; i < 8; i++)
			idleImages[i] = String.format("%s_%s_Idle0.png", prefix, i);

		final var images = new String[8];
		final var walkingAnimation = new IsometricImage[10];

		for (var frame = 0; frame < 10; frame++) {
			for (var i = 0; i < 8; i++)
				images[i] = String.format("%s_%s_Run%s.png", prefix, i, frame);

			walkingAnimation[frame] = new IsometricImage(images);
		}

		final var pickupAnimation = new IsometricImage[10];

		for (var frame = 0; frame < 10; frame++) {
			for (var i = 0; i < 8; i++)
				images[i] = String.format("%s_%s_Pickup%s.png", prefix, i, frame);

			pickupAnimation[frame] = new IsometricImage(images);
		}

		return new Model(
				new IsometricImage(idleImages),
				new Animation(walkingAnimation, walkingFrameTime),
				new CallbackAnimation(pickupAnimation, pickupFrameTime, pickupCallbackFrame));
	}
}
