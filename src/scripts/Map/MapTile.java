package scripts.Map;

import scripts.Visuals.ScaledImageObject;

public class MapTile<I> extends ScaledImageObject<I> {
	private final GridPosition gridPosition;

	public MapTile(String imageFileName, GridPosition gridPosition) {
		super(imageFileName, gridPosition.start(), gridPosition.getScale());

		this.gridPosition = gridPosition;
	}

	public GridPosition getGridPosition() {
		return gridPosition;
	}
}
