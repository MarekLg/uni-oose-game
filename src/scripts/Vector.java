package scripts;

import name.panitz.game.framework.Vertex;

/**
 * Extends {@code Vertex} with functions from linear algebra and QoL functions
 */
public class Vector extends Vertex {

	public Vector(double x, double y) {
		super(x, y);
	}

	/** Shorthand for {@code new Vector(0, 0)} */
	public static Vector zero = new Vector(0, 0);

	/** Shorthand for {@code new Vector(1, 1)} */
	public static Vector one = new Vector(1, 1);

	/** Shorthand for {@code new Vector(1, 0)} */
	public static Vector right = new Vector(1, 0);

	/** Shorthand for {@code new Vector(-1, 0)} */
	public static Vector left = new Vector(-1, 0);

	/** Shorthand for {@code new Vector(0, 1)} */
	public static Vector down = new Vector(0, 1);

	/** Shorthand for {@code new Vector(0, -1)} */
	public static Vector up = new Vector(0, -1);

	/**
	 * @param vertex {@link Vertex} to convert to {@link Vector}
	 * @see name.panitz.game.framework.Vertex
	 */
	public static Vector fromVertex(Vertex vertex) {
		return new Vector(vertex.x, vertex.y);
	}

	public double magnitude() {
		return Math.sqrt(magnitudeSqr());
	}

	public double magnitudeSqr() {
		return x * x + y * y;
	}

	public boolean isApproxZero() {
		return magnitudeSqr() < 2 * Double.MIN_VALUE;
	}

	public Vector scale(double scale) {
		return new Vector(x * scale, y * scale);
	}

	public Vector scale(Vertex other) {
		return new Vector(x * other.x, y * other.y);
	}

	public Vector normalize() {
		if (isApproxZero())
			return new Vector(0, 0);

		final var magnitude = magnitude();

		return new Vector(x / magnitude, y / magnitude);
	}

	public Vector clamp(double max) {
		if (max < 0)
			throw new IllegalArgumentException();

		if (magnitudeSqr() > max * max)
			return this.normalize().scale(max);

		return this;
	}

	public Vector negate() {
		return new Vector(-x, -y);
	}

	public Vector add(Vertex other) {
		return new Vector(x + other.x, y + other.y);
	}

	public Vector sub(Vertex other) {
		return new Vector(x - other.x, y - other.y);
	}

	public double dot(Vertex other) {
		return x * other.x + y * other.y;
	}
}
