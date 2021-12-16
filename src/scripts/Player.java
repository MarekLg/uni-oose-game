package scripts;

public class Player<I> extends CharacterObject<I> {

	public Player() {
		super(Model.createModel("sprites/player/Male"), 0.5);
	}
}
