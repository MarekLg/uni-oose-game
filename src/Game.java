import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import name.panitz.game.framework.AbstractGame;
import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.KeyCode;
import scripts.EnemySpawner;
import scripts.Globals;
import scripts.InputController;
import scripts.Vector;
import scripts.Characters.Alien;
import scripts.Characters.CharacterObject;
import scripts.Characters.Player;

public class Game<I, S> extends AbstractGame<I, S> {

	private List<CharacterObject<I>> characters = new ArrayList<>();
	private final EnemySpawner<I> spawner;

	public Game() {
		super(new Player<>(), Globals.width, Globals.height);

		// TODO: tweak spawning interval, map generation
		spawner = new EnemySpawner<>(10000, new Vector(300, 400), this::addAlien);
	}

	@Override
	public void doChecks() {
		spawner.Update();
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

		for (final var c : characters) {
			final var direction = Vector.sub(
					CharacterObject.getCenter(getPlayer()),
					c.getCenter());

			c.setVelocity(direction.magnitudeSqr() > 200
					? direction.clamped(1)
					: new Vector(0, 0));
		}

		super.move();
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		final var objectsToPaint = new ArrayList<GameObject<I>>();

		objectsToPaint.addAll(gos);
		objectsToPaint.add(getPlayer());

		objectsToPaint.sort(new Comparator<GameObject<I>>() {
			@Override
			public int compare(GameObject<I> o1, GameObject<I> o2) {
				return Double.compare(o1.getPos().y + o1.getHeight(), o2.getPos().y + o2.getHeight());
			}
		});

		for (final var go : objectsToPaint)
			go.paintTo(g);
	}

	private void addAlien(Alien<I> c) {
		gos.add(c);
		characters.add(c);
	}
}
