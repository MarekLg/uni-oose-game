import name.panitz.game.framework.AbstractGame;
import name.panitz.game.framework.KeyCode;
import scripts.InputController;
import scripts.Player;

public class Game<I, S> extends AbstractGame<I, S> {

	public Game() {
		super(new Player<>(), 1600, 900);
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
			getPlayer().setVelocity(InputController.INSTANCE.getInput().normalized().scaled(2.5));

		super.move();
	}
}
