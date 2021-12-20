import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import scripts.Map.MapGenerator;

public class Game<I, S> extends AbstractGame<I, S> {

	private List<GameObject<I>> mapTiles = new ArrayList<>();
	private List<CharacterObject<I>> aliens = new ArrayList<>();
	private final EnemySpawner<I> spawner;

	public Game() {
		super(new Player<>(), Globals.width, Globals.height);

		GenerateMap(15, 30, 0.5);

		goss.add(aliens);

		// TODO: tweak spawning interval
		spawner = new EnemySpawner<>(10000, new Vector(300, 400), aliens::add);
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
			getPlayer().setVelocity(InputController.INSTANCE.getInput().normalize().scale(3));

		for (final var c : aliens) {
			final var direction = Vector
					.fromVertex(CharacterObject.getCenter(getPlayer()))
					.sub(c.getCenter());

			c.setVelocity(direction.magnitudeSqr() > 200
					? direction.clamp(1)
					: new Vector(0, 0));
		}

		super.move();
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		for (final var mapTile : mapTiles)
			mapTile.paintTo(g);

		final var objects = new ArrayList<GameObject<I>>();

		objects.addAll(aliens);
		objects.add(getPlayer());

		objects.sort(new Comparator<GameObject<I>>() {
			@Override
			public int compare(GameObject<I> o1, GameObject<I> o2) {
				return Double.compare(o1.getPos().y + o1.getHeight(), o2.getPos().y + o2.getHeight());
			}
		});

		for (final var go : objects)
			go.paintTo(g);
	}

	private void GenerateMap(int resolutionX, int resolutionY, double scale) {
		mapTiles = new MapGenerator(resolutionX, resolutionY, scale).generate();

		goss.add(mapTiles);
	}
}
