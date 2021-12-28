package scripts.Characters;

public class Health {
	private int health;
	private final int invincibleTime;
	private int invincibleCounter;

	public Health(int initialHealth, int invincibleTime) {
		if (initialHealth < 0 || invincibleTime <= 0)
			throw new IllegalArgumentException();

		health = initialHealth;
		this.invincibleTime = invincibleTime;
	}

	public boolean Damage() {
		if (invincibleCounter <= 0) {
			health--;
			invincibleCounter = invincibleTime;
		}

		return health < 0;
	}

	public void Update() {
		if (invincibleCounter > 0)
			invincibleCounter--;
	}

	public int getHealth() {
		return health;
	}

	public boolean isCounting() {
		return invincibleCounter > 0;
	}
}
