package scripts.Map;

import name.panitz.game.framework.Vertex;
import scripts.Globals;
import scripts.Vector;

public class GridPosition {
	private int x;
	private int y;

	public GridPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// TODO: not yet working
	public static GridPosition fromScreenPosition(Vertex screenPosition) {
		final var x = (int) (screenPosition.x / (Globals.isometricBaseWidth * Globals.mapScale));
		final var y = (int) (screenPosition.y / (Globals.isometricBaseHeight * Globals.mapScale));

		return new GridPosition(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof GridPosition))
			return false;

		final var other = (GridPosition) obj;

		return x == other.x && y == other.y;
	}

	@Override
	public int hashCode() {
		// using bijective algorithm

		int tmp = (y + ((x + 1) / 2));
		return x + (tmp * tmp);
	}

	/**
	 * @return the screen position of the top left corner of the sprite
	 */
	public Vector startSprite() {
		final var offset = Globals.isometricSpriteHeight - Globals.isometricBaseHeight - Globals.isometricBaseBorder;

		return startTile().add(new Vector(0, -offset * Globals.mapScale));
	}

	/**
	 * @return the screen position of the top left corner of the tile
	 */
	public Vector startTile() {
		final var startX = x * Globals.isometricBaseWidth;
		final var startY = y * Globals.isometricBaseHeight;

		return new Vector(startX, startY).scale(Globals.mapScale);
	}

	public Vector center() {
		return startTile().add(Globals.tileSize().scale(0.5));
	}
}
