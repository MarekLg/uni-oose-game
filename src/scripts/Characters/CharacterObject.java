package scripts.Characters;

import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.Vertex;
import scripts.Direction;
import scripts.Globals;
import scripts.Vector;
import scripts.Visuals.Model;
import scripts.Visuals.ModelState;
import scripts.Visuals.ScaledImageObject;

public class CharacterObject<I> extends ScaledImageObject<I> {
	private final Model model;
	private Direction direction;

	public CharacterObject(Model model, double scale) {
		super(model.getImageForDirection(Direction.NE), scale);
		this.model = model;
		direction = Direction.NE;
	}

	public CharacterObject(Model model, Vertex position, double scale) {
		super(model.getImageForDirection(Direction.NE), position, scale);
		this.model = model;
		direction = Direction.NE;
	}

	@Override
	public void move() {
		final var v = ensureInBounds(Vector.fromVertex(getVelocity()));
		setVelocity(v);

		if (v.magnitudeSqr() > 0.001) {
			direction = Direction.fromVector(v);
			model.setState(ModelState.WALKING);
		} else
			model.setState(ModelState.IDLE);

		super.move();
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		setImageFileName(model.getImageForDirection(direction));

		super.paintTo(g);
	}

	public Vector getCenter() {
		return new Vector(Globals.isometricSpriteWidth * 0.5, Globals.isometricSpriteHeight * 0.875)
				.scale(getScale())
				.add(getPos());
	}

	private Vector ensureInBounds(Vector v) {
		final var toleranceX = 50;
		final var toleranceY = 32;
		final var pos = v.add(getPos());

		if (pos.x + toleranceX < 0 && v.x < 0
				|| pos.x + getWidth() - toleranceX > Globals.width() && v.x > 0) {
			v.x = 0;
		}

		if (pos.y + getHeight() * 0.5 + toleranceY < 0 && v.y < 0
				|| pos.y + getHeight() - toleranceY > Globals.height() && v.y > 0) {
			v.y = 0;
		}

		return v;
	}
}
