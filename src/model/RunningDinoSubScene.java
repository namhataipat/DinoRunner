package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class RunningDinoSubScene extends SubScene {

	private final static String BACKGROUND_IMAGE = ClassLoader.getSystemResource("glassPanel_cornerTL.png").toString();
	
	private boolean isHidden;
	
	public RunningDinoSubScene() {
		super(new AnchorPane(), 600, 400);

		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 450, 400, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane menuRoot = (AnchorPane) this.getRoot();
		menuRoot.setBackground(new Background(image));
		isHidden = true;
		setLayoutX(980);
		setLayoutY(160);
		
	}
	
	public void moveSubscene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		
		if(isHidden) {
			transition.setToX(-676);
			isHidden = false;
		}
		else {
			transition.setToX(300);
			isHidden = true;
		}
		
		
		transition.play();
	}

	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}

}
