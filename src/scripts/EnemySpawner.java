package scripts;

import java.util.function.Consumer;

import scripts.Characters.Alien;

public class EnemySpawner<I> {
	private final int spawningInterval;
	private final Consumer<Alien<I>> onSpawn;
	private int counter;

	public EnemySpawner(int spawningInterval, Consumer<Alien<I>> onSpawn) {
		this.spawningInterval = spawningInterval;
		this.onSpawn = onSpawn;
		counter = 0;
	}

	public void Update() {
		counter++;

		if (counter < spawningInterval)
			return;

		SpawnEnemy();
		counter = 0;
	}

	private void SpawnEnemy() {
		final var enemy = new Alien<I>();
		enemy.setCenter(getRandomBorderPosition());

		onSpawn.accept(enemy);
	}

	private static Vector getRandomBorderPosition() {
		return new Vector(
				Math.random() < 0.5 ? -32 : Globals.width() + 32,
				Math.random() * Globals.height());
	}
}
