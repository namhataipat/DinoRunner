package entity;

import entity.base.Collidable;
import entity.FallingEntity;
import javafx.scene.media.AudioClip;
import view.GameViewManager;

public class BigMeteor extends FallingEntity implements Collidable  {
	private final static String BIGMETEOR_IMG = ClassLoader.getSystemResource("meteorBrown_big.png").toString();

	public BigMeteor() {
		super(BIGMETEOR_IMG, 1, 30);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void checkIfElementsCollide(Dino d) {
		if (this.whichElementsCollide(d) != -1) {
			AudioClip damageSound = new AudioClip(ClassLoader.getSystemResource("sound/damage.wav").toString());
			damageSound.play();
			GameViewManager.removeLife();
			if (GameViewManager.getPlayerLife() >= 0) {
				GameViewManager.removeLife();
			}
			GameViewManager.setNewElementPosition(imageView[0]);
			}
		}

	
}
