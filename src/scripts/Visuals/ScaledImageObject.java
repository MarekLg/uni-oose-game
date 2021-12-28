package scripts.Visuals;

import name.panitz.game.framework.AbstractGameObject;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.Vertex;

public class ScaledImageObject<I> extends AbstractGameObject<I> {
	String imageFileName;
	I img;
	private boolean changed = true;
	private double scale;

	public ScaledImageObject(String imageFileName, double scale) {
		super(0, 0);
		this.imageFileName = imageFileName;
		this.scale = scale;
	}

	public ScaledImageObject(String imageFileName, Vertex position, double scale) {
		super(0, 0, position);
		this.imageFileName = imageFileName;
		this.scale = scale;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		if (imageFileName.equals(this.imageFileName))
			return;

		this.imageFileName = imageFileName;
		changed = true;
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		if (changed) {
			img = g.generateImage(imageFileName, this);
			changed = false;

			setWidth(getWidth() * scale);
			setHeight(getHeight() * scale);
		}

		final var pos = getPos();

		if (null != img)
			g.drawImage(img, pos.x, pos.y, (int) getWidth(), (int) getHeight());
	}

	public double getScale() {
		return scale;
	}
}
