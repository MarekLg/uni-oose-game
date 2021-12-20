package scripts.Map;

import java.util.ArrayList;
import java.util.List;

import name.panitz.game.framework.GameObject;

public class MapGenerator {
	private final int resolutionX;
	private final int resolutionY;
	private final double scale;

	public MapGenerator(int resolutionX, int resolutionY, double scale) {
		this.resolutionX = resolutionX;
		this.resolutionY = resolutionY;
		this.scale = scale;
	}

	public <I> List<GameObject<I>> generate() {
		final var map = new ArrayList<GameObject<I>>();

		for (var y = 0; y < resolutionY; y++)
			for (var x = 0; x < resolutionX; x++)
				map.add(createMapTile(new GridPosition(x, y, scale)));

		return map;
	}

	private <I> GameObject<I> createMapTile(GridPosition position) {
		final var spriteVariant = new String[] {
				"E",
				"S",
				"N",
				"W",
		}[(int) (Math.random() * 4.0)];

		final var spriteName = String.format("sprites/map/dirt_%s.png", spriteVariant);

		return new MapTile<I>(spriteName, position);
	}
}
