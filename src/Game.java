import name.panitz.game.framework.AbstractGame;
import name.panitz.game.framework.KeyCode;
import scripts.CharacterObject;
import scripts.InputController;
import scripts.IsometricImage;

public class Game<I, S> extends AbstractGame<I, S> {

	public Game() {
		super(CreatePlayer(), 1600, 900);
	}

	@Override
	public void doChecks() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressedReaction(KeyCode keycode) {
		InputController.INSTANCE.pressed(keycode);
	}

	@Override
	public void keyReleasedReaction(KeyCode keycode) {
		InputController.INSTANCE.released(keycode);
	}

	@Override
	public void move() {
		if (InputController.INSTANCE.shouldChange())
			getPlayer().setVelocity(InputController.INSTANCE.getInput().normalized());

		super.move();
	}

	private static <I> CharacterObject<I> CreatePlayer() {
		final var playerImages = new String[8];

		for (var i = 0; i < 8; i++)
			playerImages[i] = String.format("sprites/player/Male_%s_Idle0.png", i);

		return new CharacterObject<I>(new IsometricImage(playerImages), 0.5);
	}
}
