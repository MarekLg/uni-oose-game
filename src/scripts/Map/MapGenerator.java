package scripts.Map;

import java.util.ArrayList;
import java.util.List;

import scripts.Globals;

public class MapGenerator<I> {
	public MapGrid<I> generate() {
		final var tiles = generateTiles();

		return new MapGrid<I>(tiles);
	}

	private List<MapTile<I>> generateTiles() {
		final var tiles = new ArrayList<MapTile<I>>();

		for (var y = 0; y < Globals.gridResolutionX; y++)
			for (var x = 0; x < Globals.gridResolutionY; x++)
				tiles.add(createMapTile(new GridPosition(x, y)));

		return tiles;
	}

	private MapTile<I> createMapTile(GridPosition position) {
		final var spriteName = String.format("sprites/map/dirt_E.png", "E");

		return new MapTile<I>(spriteName, position);
	}
}
