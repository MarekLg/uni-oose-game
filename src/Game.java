import name.panitz.game.framework.AbstractGame;
import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Game<I, S> extends AbstractGame<I, S> {

	public Game() {
		super(new ImageObject<>("assets/character/Male_0_Idle0.png", new Vertex(200, 200)), 800, 600);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doChecks() {
		// TODO Auto-generated method stub

	}

}
