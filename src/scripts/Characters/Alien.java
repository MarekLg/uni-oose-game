package scripts.Characters;

import scripts.Model;

public class Alien<I> extends CharacterObject<I> {

	public Alien() {
		super(Model.createModel("sprites/alien/Human", 21), 0.7);
	}
}
