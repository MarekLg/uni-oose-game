package scripts;

public class Globals {
	// map
	public static final int isometricSpriteWidth = 256;
	public static final int isometricSpriteHeight = 512;
	public static final int isometricBaseWidth = 256;
	public static final int isometricBaseHeight = 180;
	public static final int isometricBaseBorder = 12;

	public static final int gridResolutionX = 18;
	public static final int gridResolutionY = 14;

	public static final double mapScale = 0.25;

	public static final int width() {
		return (int) (isometricBaseWidth * mapScale * gridResolutionX);
	}

	public static final int height() {
		return (int) (isometricBaseHeight * mapScale * gridResolutionY);
	}

	public static final Vector size() {
		return new Vector(width(), height());
	}

	// farming
	public static final int growTime = 300;

	// aliens
	public static final int alienSpawnTime = 1000;

	// player
	public static final int startingPoints = 1;
}
