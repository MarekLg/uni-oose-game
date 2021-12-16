package name.panitz.game.framework;

import java.util.List;

public interface GameLogic<I, S> extends Movable, Paintable<I> {
	List<GameObject<I>> getGOs();

	GameObject<I> getPlayer();

	List<Button> getButtons();

	void playSound(SoundObject<S> so);

	List<SoundObject<S>> getSoundsToPlayOnce();

	double getWidth();

	double getHeight();

	void doChecks();

	boolean isStopped();

	default public void paintTo(GraphicsTool<I> g) {
		for (final var go : getGOs())
			go.paintTo(g);
		getPlayer().paintTo(g);
	}

	default void move() {
		if (!isStopped()) {
			for (final var go : getGOs())
				go.move();
			getPlayer().move();
		}
	}

	default void playSounds(SoundTool<S> soundTool) {
		for (SoundObject<S> so : getSoundsToPlayOnce()) {
			so.playSound(soundTool);
		}
		getSoundsToPlayOnce().clear();
	}

	void keyPressedReaction(KeyCode keycode);

	void keyReleasedReaction(KeyCode keycode);

	void start();

	void pause();
}
