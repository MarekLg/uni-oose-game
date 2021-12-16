package scripts.Visuals;

import scripts.Direction;

public class IsometricImage {
	private final String[] imageFileNames = new String[8];

	public IsometricImage(String[] imageFileNames) {
		if (imageFileNames.length == 0)
			throw new IllegalArgumentException();

		for (var i = 0; i < this.imageFileNames.length; i++)
			this.imageFileNames[i] = imageFileNames[i % imageFileNames.length];
	}

	public String getImageForDirection(Direction direction) {
		return imageFileNames[direction.intValue()];
	}
}
