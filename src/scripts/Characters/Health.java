package scripts.Characters;

public class Health {
	private int health;
	private final int counterTime;
	private int counter;

	public Health(int initialHealth, int counterTime) {
		if (initialHealth < 0 || counterTime <= 0)
			throw new IllegalArgumentException();

		health = initialHealth;
		this.counterTime = counterTime;
	}

	public boolean Damage() {
		if (counter > 0)
			return false;

		health--;
		counter = counterTime;

		return health < 0;
	}

	public void Update() {
		if (counter > 0)
			counter--;
	}

	public int getHealth() {
		return health;
	}

	public boolean isCounting() {
		return counter > 0;
	}
}
