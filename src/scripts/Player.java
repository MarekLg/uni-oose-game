package scripts;

public class Player<I> extends CharacterObject<I> {

	public Player() {
		super(createModel(), 0.5);
	}

	private static Model createModel() {
		final var idleImages = new String[8];

		for (var i = 0; i < 8; i++)
			idleImages[i] = String.format("sprites/player/Male_%s_Idle0.png", i);

		final var walkingAnimation = new IsometricImage[10];
		final var images = new String[8];

		for (var frame = 0; frame < 10; frame++) {
			for (var i = 0; i < 8; i++)
				images[i] = String.format("sprites/player/Male_%s_Run%s.png", i, frame);

			walkingAnimation[frame] = new IsometricImage(images);
		}

		return new Model(new IsometricImage(idleImages), new Animation(walkingAnimation, 7));
	}
}
