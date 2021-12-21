package scripts.Map;

import scripts.Globals;
import scripts.Visuals.ScaledImageObject;

public class MapTile<I> extends ScaledImageObject<I> {
	private final GridPosition gridPosition;

	public MapTile(String imageFileName, GridPosition gridPosition) {
		super(imageFileName, gridPosition.startSprite(), Globals.mapScale);

		this.gridPosition = gridPosition;
	}

	public GridPosition getGridPosition() {
		return gridPosition;
	}

	public void Update() {
	}
}
