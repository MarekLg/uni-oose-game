package scripts.UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.Paintable;
import scripts.Globals;
import scripts.Vector;
import scripts.Visuals.ScaledImageObject;

public class UI<I> implements Paintable<I> {
	private List<GameObject<I>> elements = new ArrayList<>();
	private List<ScaledImageObject<I>> hearts = new ArrayList<>();

	public UI() {
		addHeartsPanel();
	}

	public List<GameObject<I>> getElements() {
		return Collections.unmodifiableList(elements);
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		for (final var element : elements)
			element.paintTo(g);
	}

	public void updateLivesCount(int count) {
		if (count < 0 || count > 3)
			throw new IllegalArgumentException();

		for (var i = 0; i < 3; i++)
			updateHeart(i, count);
	}

	private void addHeartsPanel() {
		final var sprite = "sprites/ui/panel.png";
		final var width = 46;
		final var height = 18;
		final var scale = 3;

		final var pos = Globals.size().sub(new Vector(width, height).scale(scale));

		elements.add(new ScaledImageObject<>(sprite, pos, scale));

		addHeart(pos.add(new Vector(20, 20)));
		addHeart(pos.add(new Vector(55, 20)));
		addHeart(pos.add(new Vector(90, 20)));
	}

	private void addHeart(Vector position) {
		final var scale = 0.5;

		final var heart = new ScaledImageObject<I>("sprites/ui/heart_full.png", position, scale);

		hearts.add(heart);
		elements.add(heart);
	}

	private void updateHeart(int index, int count) {
		hearts.get(index).setImageFileName("sprites/ui/heart_" + (count > index ? "full" : "empty") + ".png");
	}
}
