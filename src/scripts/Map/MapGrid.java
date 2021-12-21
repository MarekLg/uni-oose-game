package scripts.Map;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import name.panitz.game.framework.GraphicsTool;
import scripts.CompassDirection;
import scripts.Visuals.ScaledImageObject;

public class MapGrid<I> {
	private final LinkedHashMap<GridPosition, MapTile<I>> tiles;
	private final Map<CompassDirection, List<ScaledImageObject<I>>> fences;

	public MapGrid(List<MapTile<I>> tiles, Map<CompassDirection, List<ScaledImageObject<I>>> fences) {
		this.tiles = tiles
				.stream()
				.collect(Collectors.toMap(
						tile -> tile.getGridPosition(),
						Function.identity(),
						(t1, t2) -> {
							throw new IllegalStateException(String.format("Duplicate key %s", t1));
						},
						LinkedHashMap::new));

		if (fences.keySet().size() < 4)
			throw new IllegalArgumentException();

		this.fences = fences;
	}

	public Collection<MapTile<I>> getTiles() {
		return Collections.unmodifiableCollection(tiles.values());
	}

	public void paintBackgroundTo(GraphicsTool<I> g) {
		for (final var tile : tiles.values())
			tile.paintTo(g);

		// g.setColor(0.62, 0.45, 0.3); // TODO: maybe black with low alpha better

		// for (var gridX = 0; gridX < Globals.gridResolutionX; gridX++) {
		// final var x = gridX * Globals.isometricBaseWidth * Globals.mapScale;
		// g.drawLine(x, 0, x, Globals.height());
		// }

		// for (var gridY = 0; gridY < Globals.gridResolutionY; gridY++) {
		// final var y = gridY * Globals.isometricBaseHeight * Globals.mapScale;
		// g.drawLine(0, y, Globals.width(), y);
		// }

		paintFencesTo(g, CompassDirection.N);
		paintFencesTo(g, CompassDirection.W);
		paintFencesTo(g, CompassDirection.E);

	}

	public void paintForegroundTo(GraphicsTool<I> g) {
		paintFencesTo(g, CompassDirection.S);
	}

	public void paintFencesTo(GraphicsTool<I> g, CompassDirection direction) {
		for (final var fence : fences.get(direction))
			fence.paintTo(g);
	}

	public MapTile<I> getTile(GridPosition position) {
		return tiles.get(position);
	}

	public void Update() {
	}
}
