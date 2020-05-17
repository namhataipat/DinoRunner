package entity;

import entity.base.Collidable;
import entity.FallingEntity;
import javafx.scene.media.AudioClip;
import view.GameViewManager;

public class Meteor extends FallingEntity implements Collidable  {
	
	private final static String METEOR_IMG = ClassLoader.getSystemResource("meteorBrown_med.png").toString();

	public Meteor() { 
		super(METEOR_IMG, 5, 20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkIfElementsCollide(Dino d) {
		int e = this.whichElementsCollide(d);
		if (e != -1) {
			AudioClip damageSound = new AudioClip(ClassLoader.getSystemResource("sound/damage.wav").toString());
			damageSound.play();
			GameViewManager.removeLife();
			GameViewManager.setNewElementPosition(imageView[e]);
			}
		}
		
	}

	
