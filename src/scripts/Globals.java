package scripts;

public class Globals {
	public static final int isometricSpriteWidth = 256;
	public static final int isometricSpriteHeight = 512;
	public static final int isometricBaseWidth = 256;
	public static final int isometricBaseHeight = 180;
	public static final int isometricBaseBorder = 12;

	public static final int gridResolutionX = 24;
	public static final int gridResolutionY = 20;

	public static final double mapScale = 0.25;

	public static final int width() {
		return (int) (isometricBaseWidth * mapScale * gridResolutionX);
	}

	public static final int height() {
		return (int) (isometricBaseHeight * mapScale * gridResolutionY);
	}
}
