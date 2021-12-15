package scripts;

import java.util.ArrayList;
import java.util.List;

import name.panitz.game.framework.KeyCode;

public enum InputController {
	INSTANCE;

	private Vector input;
	private boolean dirty;
	private List<KeyCode> pressedKeys;

	private InputController() {
		input = new Vector(0, 0);
		dirty = true;
		pressedKeys = new ArrayList<>();
	}

	public void pressed(KeyCode keycode) {
		if (keycode == null || pressedKeys.contains(keycode))
			return;

		switch (keycode) {
			case RIGHT_ARROW:
				input.move(new Vector(1, 0));
				break;
			case LEFT_ARROW:
				input.move(new Vector(-1, 0));
				break;
			case DOWN_ARROW:
				input.move(new Vector(0, 1));
				break;
			case UP_ARROW:
				input.move(new Vector(0, -1));
				break;
			default:
				break;
		}

		pressedKeys.add(keycode);
		dirty = true;
	}

	public void released(KeyCode keycode) {
		if (keycode == null)
			return;

		switch (keycode) {
			case RIGHT_ARROW:
				input.move(new Vector(-1, 0));
				break;
			case LEFT_ARROW:
				input.move(new Vector(1, 0));
				break;
			case DOWN_ARROW:
				input.move(new Vector(0, -1));
				break;
			case UP_ARROW:
				input.move(new Vector(0, 1));
				break;
			default:
				break;
		}

		pressedKeys.remove(keycode);
		dirty = true;
	}

	public boolean shouldChange() {
		if (!dirty)
			return false;

		dirty = false;

		return true;
	}

	public Vector getInput() {
		return input;
	}
}
