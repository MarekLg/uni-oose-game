package scripts;

import name.panitz.game.framework.Vertex;

/**
 * Extends {@code Vertex} with functions from linear algebra and QoL functions
 */
public class Vector extends Vertex {

	public Vector(double x, double y) {
		super(x, y);
	}

	public static Vector fromVertex(Vertex vertex) {
		return new Vector(vertex.x, vertex.y);
	}

	public void scale(double scale) {
		if (isApproxZero())
			return;

		x *= scale;
		y *= scale;
	}

	public Vector scaled(double scale) {
		if (isApproxZero())
			return new Vector(0, 0);

		final var result = new Vector(x, y);

		result.scale(scale);

		return result;
	}

	public void normalize() {
		if (isApproxZero())
			return;

		final var magnitude = magnitude();

		x /= magnitude;
		y /= magnitude;
	}

	public Vector normalized() {
		if (isApproxZero())
			return new Vector(0, 0);

		final var result = new Vector(x, y);

		result.normalize();

		return result;
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

	public Vector clamped(double max) {
		if (max < 0)
			throw new IllegalArgumentException();

		if (magnitudeSqr() > max * max)
			return this.normalized().scaled(max);

		return this;
	}

	public static Vector negate(Vertex v) {
		return new Vector(-v.x, -v.y);
	}

	public static Vector add(Vertex v1, Vertex v2) {
		return new Vector(v1.x + v2.x, v1.y + v2.y);
	}

	public static Vector sub(Vertex v1, Vertex v2) {
		return add(v1, negate(v2));
	}

	public Vector negated() {
		return negate(this);
	}

	public Vector add(Vector other) {
		return add(this, other);
	}

	public Vector sub(Vector other) {
		return sub(this, other);
	}
}
