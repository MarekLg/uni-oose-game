package scripts;

import java.util.function.Consumer;

import scripts.Characters.Alien;

public class EnemySpawner<I> {
	private final int spawningInterval;
	private final Vector spawnPosition;
	private final Consumer<Alien<I>> onSpawn;
	private int counter;

	public EnemySpawner(int spawningInterval, Vector spawnPosition, Consumer<Alien<I>> onSpawn) {
		this.spawningInterval = spawningInterval;
		this.spawnPosition = spawnPosition;
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
		enemy.getPos().moveTo(spawnPosition);

		onSpawn.accept(enemy);
	}
}
