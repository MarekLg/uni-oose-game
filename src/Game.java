import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import name.panitz.game.framework.AbstractGame;
import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.KeyCode;
import scripts.EnemySpawner;
import scripts.Globals;
import scripts.InputController;
import scripts.Vector;
import scripts.Characters.Alien;
import scripts.Characters.Player;
import scripts.Map.Corn;
import scripts.Map.GridPosition;
import scripts.Map.MapGenerator;
import scripts.Map.MapGrid;
import scripts.UI.UI;
import scripts.Visuals.ScaledImageObject;

public class Game<I, S> extends AbstractGame<I, S> {

	private InputController input;

	private Player<I> player;
	private MapGrid<I> map;
	private UI<I> ui;
	private Map<GridPosition, Corn<I>> corns = new HashMap<>();
	private List<Alien<I>> aliens = new ArrayList<>();
	private final EnemySpawner<I> spawner;

	public Game() {
		super(new Player<>(), Globals.mapWidth(), Globals.mapHeight());

		input = new InputController(this::farm);

		player = (Player<I>) getPlayer();

		map = new MapGenerator<I>().generate();

		ui = new UI<I>();

		goss.add(aliens);

		spawner = new EnemySpawner<>(Globals.alienSpawnTime, aliens::add);
	}

	@Override
	public void doChecks() {
		if (player.isDead())
			return;

		spawner.Update();

		player.Update();

		for (final var corn : corns.values())
			corn.update();

		for (final var alien : aliens)
			if (alien.touches(player))
				if (!player.Damage())
					ui.updateLivesCount(player.getHealth());
	}

	@Override
	public void keyPressedReaction(KeyCode keycode) {
		if (player.isDead() && keycode == KeyCode.VK_SPACE)
			System.exit(0);

		input.pressed(keycode);
	}

	@Override
	public void keyReleasedReaction(KeyCode keycode) {
		input.released(keycode);
	}

	@Override
	public void move() {
		if (player.isDead()) {
			player.setVelocity(Vector.zero);

			for (final var alien : aliens)
				alien.setVelocity(Vector.zero);

			super.move();
			return;
		}

		getPlayer().setVelocity(input.getMovement().normalize().scale(3));

		for (final var alien : aliens) {
			final var direction = Vector
					.from(player.getCenter())
					.sub(alien.getCenter());

			alien.setVelocity(direction.magnitudeSqr() > 200
					? direction.clamp(1)
					: new Vector(0, 0));
		}

		super.move();
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		map.paintBackgroundTo(g);

		final var objects = new ArrayList<GameObject<I>>();

		objects.addAll(corns.values());
		objects.addAll(aliens);

		if (!player.isDead())
			objects.add(getPlayer());

		objects.sort(new Comparator<GameObject<I>>() {
			@Override
			public int compare(GameObject<I> o1, GameObject<I> o2) {
				return Double.compare(o1.getPos().y + o1.getHeight(), o2.getPos().y + o2.getHeight());
			}
		});

		for (final var go : objects)
			go.paintTo(g);

		map.paintForegroundTo(g);

		ui.paintTo(g);

		if (player.isDead()) {
			new ScaledImageObject<I>("sprites/ui/background.png", Math.max(Globals.mapWidth(), Globals.mapHeight()))
					.paintTo(g);

			g.setColor(0.9, 0.5, 0.45);
			g.drawString(200, 300, 64, "Game Over");
			g.setColor(1, 1, 1);
			g.drawString(203, 335, 20, "Press space to close.");
		}
	}

	private void farm() {
		final var position = GridPosition.fromScreenPosition(player.getCenter());

		if (corns.containsKey(position)) {
			final var corn = corns.get(position);

			if (!corn.isDone())
				return;

			player.addPoint();

			corn.harvest();
		} else if (player.removePoint()) {
			final var tile = map.getTile(position);

			corns.put(position, new Corn<I>(tile));
		}

		ui.updatePoints(player.points);
	}
}
