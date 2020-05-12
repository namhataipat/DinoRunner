package entity;

import entity.base.Collidable;
import entity.base.FallingEntity;
import javafx.scene.media.AudioClip;
import model.Dinosaur;
import view.GameViewManager;

public class YourFood extends FallingEntity implements Collidable {
	
	public YourFood(Dinosaur chosenDino) {
		super(chosenDino.getUrlFood(), 2, 20);
	}

	@Override
	public void checkIfElementsCollide(Dino d) {
		if (this.isElementsCollide(d)) {
			AudioClip getpointSound = new AudioClip(ClassLoader.getSystemResource("sound/getpoint.wav").toString());
			getpointSound.play();
			for (int i = 0; i < imageView.length; i++) {
				this.setNewElementPosition(imageView[i]);
			}
			GameViewManager.increasePoints();
			String textToSet = "POINTS   ";
			if (GameViewManager.getPoints()<10) {
				textToSet = textToSet + "0";
			}
			GameViewManager.setPointLabel(textToSet + GameViewManager.getPoints());
		}
		
	}

}
