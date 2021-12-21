package scripts.Map;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.Paintable;
import scripts.Globals;

public class MapGrid<I> implements Paintable<I> {
	private final LinkedHashMap<GridPosition, MapTile<I>> tiles;

	public MapGrid(List<MapTile<I>> tiles) {
		this.tiles = tiles
				.stream()
				.collect(Collectors.toMap(
						tile -> tile.getGridPosition(),
						Function.identity(),
						(t1, t2) -> {
							throw new IllegalStateException(String.format("Duplicate key %s", t1));
						},
						LinkedHashMap::new));
	}

	public Collection<MapTile<I>> getTiles() {
		return Collections.unmodifiableCollection(tiles.values());
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		for (final var tile : tiles.values())
			tile.paintTo(g);

		g.setColor(0.62, 0.45, 0.3); // TODO: maybe black with low alpha better

		for (var gridX = 0; gridX < Globals.gridResolutionX; gridX++) {
			final var x = gridX * Globals.isometricBaseWidth * Globals.mapScale;
			g.drawLine(x, 0, x, Globals.height());
		}

		for (var gridY = 0; gridY < Globals.gridResolutionY; gridY++) {
			final var y = gridY * Globals.isometricBaseHeight * Globals.mapScale;
			g.drawLine(0, y, Globals.width(), y);
		}
	}

	public MapTile<I> getTile(GridPosition position) {
		return tiles.get(position);
	}

	public void Update() {
	}
}
