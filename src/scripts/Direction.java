package scripts;

public enum Direction {
	NE(0, new Vector(1, 1).normalized()),
	E(1, new Vector(1, 0)),
	SE(2, new Vector(1, -1).normalized()),
	S(3, new Vector(0, -1)),
	SW(4, new Vector(-1, -1).normalized()),
	W(5, new Vector(-1, 0)),
	NW(6, new Vector(-1, 1).normalized()),
	N(7, new Vector(0, 1));

	public static Direction fromVector(Vector vector) {
		final var angle = Math.atan2(vector.y, vector.x);
		final var octant = (int) Math.round(8 * angle / (2 * Math.PI) + 9) % 8;

		return new Direction[] { NE, E, SE, S, SW, W, NW, N }[octant];
	}

	private final int index;
	private final Vector vector;

	private Direction(int index, Vector vector) {
		this.index = index;
		this.vector = vector;
	}

	public int intValue() {
		return index;
	}

	public Vector vectorValue() {
		return vector;
	}
}
