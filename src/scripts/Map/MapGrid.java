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

		paintFencesTo(g, CompassDirection.N);
		paintFencesTo(g, CompassDirection.W);
		paintFencesTo(g, CompassDirection.E);

	}

	public void paintForegroundTo(GraphicsTool<I> g) {
		paintFencesTo(g, CompassDirection.S);
	}

	private void paintFencesTo(GraphicsTool<I> g, CompassDirection direction) {
		for (final var fence : fences.get(direction))
			fence.paintTo(g);
	}

	public MapTile<I> getTile(GridPosition position) {
		if (!tiles.containsKey(position))
			throw new IllegalArgumentException();

		return tiles.get(position);
	}
}
