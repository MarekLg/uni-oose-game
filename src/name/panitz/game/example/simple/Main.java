package name.panitz.game.example.simple;

import name.panitz.game.framework.swing.SwingGame;

public class Main {
	public static void main(String... args) {
		SwingGame.startGame(new SimpleGame<>());;
	}
}
