package scripts.UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.Paintable;
import name.panitz.game.framework.TextObject;
import scripts.Globals;
import scripts.Vector;
import scripts.Visuals.ScaledImageObject;

public class UI<I> implements Paintable<I> {
	private List<GameObject<I>> elements = new ArrayList<>();
	private List<ScaledImageObject<I>> hearts = new ArrayList<>();
	private TextObject<I> pointsText;

	public UI() {
		addPanel();
	}

	public List<GameObject<I>> getElements() {
		return Collections.unmodifiableList(elements);
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		g.setColor(1, 1, 1);

		for (final var element : elements)
			element.paintTo(g);
	}

	public void updateLivesCount(int count) {
		if (count < 0 || count > 3)
			throw new IllegalArgumentException();

		for (var i = 0; i < 3; i++)
			updateHeart(i, count);
	}

	public void updatePoints(int points) {
		pointsText.text = String.valueOf(points);
	}

	private void addPanel() {
		final var sprite = "sprites/ui/panel.png";
		final var width = 93;
		final var height = 18;
		final var scale = 3;

		final var pos = Globals.mapSize().sub(new Vector(width, height).scale(scale));

		elements.add(new ScaledImageObject<>(sprite, pos, scale));

		addHeart(pos.add(new Vector(30, 20)));
		addHeart(pos.add(new Vector(65, 20)));
		addHeart(pos.add(new Vector(100, 20)));

		addSack(pos.add(new Vector(162, 12)));
		addPoints(pos.add(new Vector(210, 42)));
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

	private void addSack(Vector position) {
		final var scale = 0.5;

		final var sack = new ScaledImageObject<I>("sprites/ui/sack_N.png", position, scale);

		elements.add(sack);
	}

	private void addPoints(Vector position) {
		final var size = 30;
		final var font = "Helvetica";
		final var text = String.valueOf(Globals.startingPoints);

		pointsText = new TextObject<>(position, text, font, size);

		elements.add(pointsText);
	}
}
