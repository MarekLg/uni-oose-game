package scripts;

import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.Vertex;

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

		if (!vector.isApproxZero()) {
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
}
