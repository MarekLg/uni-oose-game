package scripts.Characters;

import name.panitz.game.framework.GraphicsTool;
import scripts.Globals;
import scripts.Visuals.Model;

public class Player<I> extends CharacterObject<I> {

	public int points;
	private final Health health;
	private int timer = 5;

	public Player() {
		super(Model.createModel("sprites/player/Male", 7), 0.5);

		health = new Health(3, 100);
		points = Globals.startingPoints;
		setCenter(Globals.startingPosition);
	}

	public void Update() {
		health.Update();

		if (health.isCounting()) {
			timer--;

			if (timer < -5)
				timer = 5;
		} else
			timer = 5;
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		if (timer > 0)
			super.paintTo(g);
	}

	/** @return {@code true}, if health is depleted */
	public boolean Damage() {
		return health.Damage();
	}

	public boolean isDead() {
		return getHealth() < 0;
	}

	public int getHealth() {
		return health.getHealth();
	}

	public int getPoints() {
		return points;
	}

	public boolean removePoint() {
		if (points <= 0)
			return false;

		points--;

		return true;
	}

	public void addPoint() {
		points++;
	}
}
