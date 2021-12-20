package scripts.Map;

import scripts.Globals;
import scripts.Vector;

public class GridPosition {
	private int x;
	private int y;
	private double scale;

	public GridPosition(int x, int y) {
		this(x, y, 1.0);
	}

	public GridPosition(int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getScale() {
		return scale;
	}

	// public static GridPosition fromPixels(int x, int y) {
	// // TODO
	// }

	public Vector center() {
		final var horizontalOffset = (y % 2) * Globals.isometricBaseWidth / 2;
		final var centerX = x * Globals.isometricBaseWidth + horizontalOffset;

		final var centerY = y * Globals.isometricBaseHeight / 2;

		return new Vector(centerX, centerY).scale(scale);
	}

	public Vector start() {
		final var startX = -Globals.isometricSpriteWidth / 2;
		final var startY = -(int) (Globals.isometricSpriteHeight * 0.875);

		return new Vector(startX, startY).scale(scale).add(center());
	}
}
