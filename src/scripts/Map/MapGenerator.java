package scripts.Map;

import java.util.ArrayList;
import java.util.List;

public class MapGenerator {
	private final int resolutionX;
	private final int resolutionY;

	public MapGenerator(int resolutionX, int resolutionY) {
		this.resolutionX = resolutionX;
		this.resolutionY = resolutionY;
	}

	public <I> List<MapTile<I>> generate() {
		final var map = new ArrayList<MapTile<I>>();

		for (var y = 0; y < resolutionY; y++)
			for (var x = 0; x < resolutionX; x++)
				map.add(createMapTile(new GridPosition(x, y)));

		return map;
	}

	private <I> MapTile<I> createMapTile(GridPosition position) {
		final var spriteName = String.format("sprites/map/dirt_E.png", "E");

		return new MapTile<I>(spriteName, position);
	}
}
