package entity;

import entity.base.Collidable;
import entity.FallingEntity;
import javafx.scene.media.AudioClip;
import model.Dinosaur;
import view.GameViewManager;

public class YourFood extends FallingEntity implements Collidable {
	
	public YourFood(Dinosaur chosenDino) {
		super(chosenDino.getUrlFood(), 3, 20);
	}

	@Override
	public void checkIfElementsCollide(Dino d) {
		int e = this.whichElementsCollide(d);
		if (e != -1) {
			AudioClip getpointSound = new AudioClip(ClassLoader.getSystemResource("sound/getpoint.wav").toString());
			getpointSound.play();
			GameViewManager.setNewElementPosition(imageView[e]);
			GameViewManager.increasePoints();
			String textToSet = "POINTS   ";
			if (GameViewManager.getPoints()<10) {
				textToSet = textToSet + "0";
			}
			GameViewManager.setPointLabel(textToSet + GameViewManager.getPoints());
		}
		
	}

}
