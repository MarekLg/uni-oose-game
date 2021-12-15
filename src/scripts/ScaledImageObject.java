package scripts;

import name.panitz.game.framework.AbstractGameObject;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class ScaledImageObject<I> extends ImageObject<I> {
	private double scale;

	public ScaledImageObject(String imageFileName, double scale) {
		super(imageFileName);
		this.scale = scale;
	}

	public ScaledImageObject(String imageFileName, Vertex position, double scale) {
		super(imageFileName, position);
		this.scale = scale;
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		if (UpdateImage(g)) {
			setWidth(getWidth() * scale);
			setHeight(getHeight() * scale);
		}

		final var img = getImage();
		final var pos = getPos();

		if (null != img)
			g.drawImage(img, pos.x, pos.y, (int) getWidth(), (int) getHeight());
	}
}
