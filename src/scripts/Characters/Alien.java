package scripts.Characters;

import scripts.Globals;
import scripts.Vector;
import scripts.Map.GridPosition;
import scripts.Visuals.Model;

public class Alien<I> extends CharacterObject<I> {

	private final Vector initialTarget;
	private boolean initialTargetReached;

	public Alien() {
		super(Model.createModel("sprites/alien/Human", 21), Globals.alienScale);

		initialTarget = GridPosition.random().center();
		initialTargetReached = false;
	}

	public void updateVelocity(Vector target) {
		if (!initialTargetReached) {
			updateVelocityInitial(target);
			return;
		}

		final var direction = target.sub(getCenter());

		final var velocity = isInRange(direction.magnitudeSqr())
				? direction.clamp(1)
				: Vector.zero;

		setVelocity(velocity);
	}

	private void updateVelocityInitial(Vector target) {
		final var center = getCenter();
		final var direction = initialTarget.sub(center);
		final var targetDirection = target.sub(center);

		if (isTooClose(direction.magnitudeSqr())
				|| !isTooFar(targetDirection.magnitudeSqr()))
			initialTargetReached = true;

		setVelocity(direction.clamp(Globals.alienMaxSpeed));
	}

	private boolean isTooClose(double distanceSqr) {
		return distanceSqr < Globals.alienTargetMinDistance * Globals.alienTargetMinDistance;
	}

	private boolean isTooFar(double distanceSqr) {
		return distanceSqr > Globals.alienAgroRange * Globals.alienAgroRange;
	}

	private boolean isInRange(double distanceSqr) {
		return !isTooClose(distanceSqr) && !isTooFar(distanceSqr);
	}
}
