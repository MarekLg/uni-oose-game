package scripts.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import scripts.CompassDirection;
import scripts.Globals;
import scripts.Visuals.ScaledImageObject;

public class MapGenerator<I> {
	public MapGrid<I> generate() {
		final var tiles = generateTiles();
		final var fences = generateFences();

		return new MapGrid<I>(tiles, fences);
	}

	private List<MapTile<I>> generateTiles() {
		final var tiles = new ArrayList<MapTile<I>>();

		for (var y = 0; y < Globals.gridResolutionY; y++)
			for (var x = 0; x < Globals.gridResolutionX; x++)
				tiles.add(new MapTile<I>("sprites/map/dirt_E.png", new GridPosition(x, y)));

		return tiles;
	}

	private Map<CompassDirection, List<ScaledImageObject<I>>> generateFences() {
		final Map<CompassDirection, List<ScaledImageObject<I>>> map = Map.of(
				CompassDirection.N, new ArrayList<ScaledImageObject<I>>(),
				CompassDirection.W, new ArrayList<ScaledImageObject<I>>(),
				CompassDirection.E, new ArrayList<ScaledImageObject<I>>(),
				CompassDirection.S, new ArrayList<ScaledImageObject<I>>());

		for (var x = 1; x < Globals.gridResolutionX - 1; x++) {
			map.get(CompassDirection.N).add(new ScaledImageObject<I>("sprites/map/fenceLow_N.png",
					new GridPosition(x, 0).startSprite(),
					Globals.mapScale));
			map.get(CompassDirection.S).add(new ScaledImageObject<I>("sprites/map/fenceLow_S.png",
					new GridPosition(x, Globals.gridResolutionY - 1).startSprite(),
					Globals.mapScale));
		}

		for (var y = 1; y < Globals.gridResolutionY - 1; y++) {
			map.get(CompassDirection.W).add(new ScaledImageObject<I>("sprites/map/fenceLow_W.png",
					new GridPosition(0, y).startSprite(),
					Globals.mapScale));
			map.get(CompassDirection.E).add(new ScaledImageObject<I>("sprites/map/fenceLow_E.png",
					new GridPosition(Globals.gridResolutionX - 1, y).startSprite(),
					Globals.mapScale));
		}

		return map;
	}
}
