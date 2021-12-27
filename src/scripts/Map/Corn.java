package scripts.Map;

import scripts.Globals;
import scripts.Visuals.ScaledImageObject;

public class Corn<I> extends ScaledImageObject<I> {
	private int phase;
	private int harvestCount;
	private int timer;

	public Corn(MapTile<I> mapTile) {
		super("", mapTile.getPos(), Globals.mapScale);

		mapTile.setImageFileName("sprites/map/dirtFarmland_E.png");

		phase = 0;
		harvestCount = 0;
		resetTimer();
	}

	public void update() {
		if (phase < 2)
			timer--;

		if (timer <= 0) {
			grow();

			resetTimer();
		}
	}

	public void harvest() {
		phase = 0;
		harvestCount++;
		updateSprite();
		resetTimer();
	}

	public boolean isDone() {
		return phase >= 2;
	}

	private void grow() {
		phase++;

		updateSprite();
	}

	private void updateSprite() {
		setImageFileName(new String[] {
				"",
				"sprites/map/cornYoung_E.png",
				"sprites/map/corn_E.png"
		}[phase]);
	}

	private void resetTimer() {
		timer = Globals.growTime * (int) Math.pow(2, harvestCount);
	}
}
