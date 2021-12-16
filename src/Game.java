import java.util.ArrayList;
import java.util.Arrays;

import name.panitz.game.framework.AbstractGame;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.KeyCode;
import name.panitz.game.framework.Paintable;
import scripts.InputController;
import scripts.Characters.Alien;
import scripts.Characters.Player;

public class Game<I, S> extends AbstractGame<I, S> {

	public Game() {
		super(new Player<>(), 1600, 900);

		gos.add(new Alien<>());
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
			getPlayer().setVelocity(InputController.INSTANCE.getInput().normalized().scaled(3));

		super.move();
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		final var objectsToPaint = new ArrayList<Paintable<I>>();

		objectsToPaint.addAll(gos);
		objectsToPaint.add(getPlayer());
	}
}
