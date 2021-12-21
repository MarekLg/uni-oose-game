package scripts;

import java.util.ArrayList;
import java.util.List;

import name.panitz.game.framework.KeyCode;

public class InputController {
	private Vector movement;
	private Runnable actionPressed;
	private List<KeyCode> pressedKeys;

	public InputController(Runnable actionPressed) {
		this.actionPressed = actionPressed;

		movement = new Vector(0, 0);
		pressedKeys = new ArrayList<>();
	}

	public void pressed(KeyCode keycode) {
		if (keycode == null || pressedKeys.contains(keycode))
			return;

		switch (keycode) {
			case RIGHT_ARROW:
				movement.move(new Vector(1, 0));
				break;
			case LEFT_ARROW:
				movement.move(new Vector(-1, 0));
				break;
			case DOWN_ARROW:
				movement.move(new Vector(0, 1));
				break;
			case UP_ARROW:
				movement.move(new Vector(0, -1));
				break;
			case VK_SPACE:
				actionPressed.run();
				break;
			default:
				break;
		}

		pressedKeys.add(keycode);
	}

	public void released(KeyCode keycode) {
		if (keycode == null)
			return;

		switch (keycode) {
			case RIGHT_ARROW:
				movement.move(new Vector(-1, 0));
				break;
			case LEFT_ARROW:
				movement.move(new Vector(1, 0));
				break;
			case DOWN_ARROW:
				movement.move(new Vector(0, -1));
				break;
			case UP_ARROW:
				movement.move(new Vector(0, 1));
				break;
			default:
				break;
		}

		pressedKeys.remove(keycode);
	}

	public Vector getMovement() {
		return movement;
	}
}
