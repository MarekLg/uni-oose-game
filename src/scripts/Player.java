package scripts;

public class Player<I> extends CharacterObject<I> {

	public Player() {
		super(createModel(), 0.5);
	}

	private static Model createModel() {
		return new Model(
				new IsometricImage(new String[] {
						"sprites/player/Male_0_Idle0.png",
						"sprites/player/Male_1_Idle0.png",
						"sprites/player/Male_2_Idle0.png",
						"sprites/player/Male_3_Idle0.png",
						"sprites/player/Male_4_Idle0.png",
						"sprites/player/Male_5_Idle0.png",
						"sprites/player/Male_6_Idle0.png",
						"sprites/player/Male_7_Idle0.png",
				}),
				new Animation(new IsometricImage[] {
						new IsometricImage(new String[] {
								"sprites/player/Male_0_Run0.png",
								"sprites/player/Male_1_Run0.png",
								"sprites/player/Male_2_Run0.png",
								"sprites/player/Male_3_Run0.png",
								"sprites/player/Male_4_Run0.png",
								"sprites/player/Male_5_Run0.png",
								"sprites/player/Male_6_Run0.png",
								"sprites/player/Male_7_Run0.png",
						}),
						new IsometricImage(new String[] {
								"sprites/player/Male_0_Run1.png",
								"sprites/player/Male_1_Run1.png",
								"sprites/player/Male_2_Run1.png",
								"sprites/player/Male_3_Run1.png",
								"sprites/player/Male_4_Run1.png",
								"sprites/player/Male_5_Run1.png",
								"sprites/player/Male_6_Run1.png",
								"sprites/player/Male_7_Run1.png",
						}),
						new IsometricImage(new String[] {
								"sprites/player/Male_0_Run2.png",
								"sprites/player/Male_1_Run2.png",
								"sprites/player/Male_2_Run2.png",
								"sprites/player/Male_3_Run2.png",
								"sprites/player/Male_4_Run2.png",
								"sprites/player/Male_5_Run2.png",
								"sprites/player/Male_6_Run2.png",
								"sprites/player/Male_7_Run2.png",
						}),
						new IsometricImage(new String[] {
								"sprites/player/Male_0_Run3.png",
								"sprites/player/Male_1_Run3.png",
								"sprites/player/Male_2_Run3.png",
								"sprites/player/Male_3_Run3.png",
								"sprites/player/Male_4_Run3.png",
								"sprites/player/Male_5_Run3.png",
								"sprites/player/Male_6_Run3.png",
								"sprites/player/Male_7_Run3.png",
						}),
						new IsometricImage(new String[] {
								"sprites/player/Male_0_Run4.png",
								"sprites/player/Male_1_Run4.png",
								"sprites/player/Male_2_Run4.png",
								"sprites/player/Male_3_Run4.png",
								"sprites/player/Male_4_Run4.png",
								"sprites/player/Male_5_Run4.png",
								"sprites/player/Male_6_Run4.png",
								"sprites/player/Male_7_Run4.png",
						}),
						new IsometricImage(new String[] {
								"sprites/player/Male_0_Run5.png",
								"sprites/player/Male_1_Run5.png",
								"sprites/player/Male_2_Run5.png",
								"sprites/player/Male_3_Run5.png",
								"sprites/player/Male_4_Run5.png",
								"sprites/player/Male_5_Run5.png",
								"sprites/player/Male_6_Run5.png",
								"sprites/player/Male_7_Run5.png",
						}),
						new IsometricImage(new String[] {
								"sprites/player/Male_0_Run6.png",
								"sprites/player/Male_1_Run6.png",
								"sprites/player/Male_2_Run6.png",
								"sprites/player/Male_3_Run6.png",
								"sprites/player/Male_4_Run6.png",
								"sprites/player/Male_5_Run6.png",
								"sprites/player/Male_6_Run6.png",
								"sprites/player/Male_7_Run6.png",
						}),
						new IsometricImage(new String[] {
								"sprites/player/Male_0_Run7.png",
								"sprites/player/Male_1_Run7.png",
								"sprites/player/Male_2_Run7.png",
								"sprites/player/Male_3_Run7.png",
								"sprites/player/Male_4_Run7.png",
								"sprites/player/Male_5_Run7.png",
								"sprites/player/Male_6_Run7.png",
								"sprites/player/Male_7_Run7.png",
						}),
						new IsometricImage(new String[] {
								"sprites/player/Male_0_Run8.png",
								"sprites/player/Male_1_Run8.png",
								"sprites/player/Male_2_Run8.png",
								"sprites/player/Male_3_Run8.png",
								"sprites/player/Male_4_Run8.png",
								"sprites/player/Male_5_Run8.png",
								"sprites/player/Male_6_Run8.png",
								"sprites/player/Male_7_Run8.png",
						}),
						new IsometricImage(new String[] {
								"sprites/player/Male_0_Run9.png",
								"sprites/player/Male_1_Run9.png",
								"sprites/player/Male_2_Run9.png",
								"sprites/player/Male_3_Run9.png",
								"sprites/player/Male_4_Run9.png",
								"sprites/player/Male_5_Run9.png",
								"sprites/player/Male_6_Run9.png",
								"sprites/player/Male_7_Run9.png",
						}),
				}, 10));
	}
}
