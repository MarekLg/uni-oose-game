package scripts.Characters;

import scripts.Visuals.Model;

public class Player<I> extends CharacterObject<I> {

	public Player() {
		super(Model.createModel("sprites/player/Male", 7), 0.5);
	}
}
