package scripts.Characters;

import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.Vertex;
import scripts.Direction;
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
	public void setVelocity(Vertex v) {
		super.setVelocity(v);

		final var vector = Vector.fromVertex(v);

		if (vector.magnitudeSqr() > 0.001) {
			direction = Direction.fromVector(vector);
			model.setState(ModelState.WALKING);
		} else
			model.setState(ModelState.IDLE);
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		setImageFileName(model.getImageForDirection(direction));

		super.paintTo(g);
	}

	public static <I> Vector getCenter(GameObject<I> go) {
		return Vector.fromVertex(go.getPos()).add(new Vector(go.getWidth() * 0.5, go.getHeight() * 0.9));
	}

	public Vector getCenter() {
		return getCenter(this);
	}
}
