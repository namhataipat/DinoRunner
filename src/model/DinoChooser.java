package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class DinoChooser extends VBox {
	
	private ImageView circleImage;
	private ImageView dinoImage;
	private String circleNotChosen = ClassLoader.getSystemResource("dinochooser/grey_circle.png").toString();
	private String circleChosen = ClassLoader.getSystemResource("dinochooser/blue_boxTick.png").toString();
	private Dinosaur dino;
	private boolean isCircleChosen;
	
	public DinoChooser(Dinosaur dino) {
		circleImage = new ImageView(circleNotChosen);
		dinoImage = new ImageView(new Image(dino.getUrlDino(), 60, 60, false, true));
		dinoImage.setPreserveRatio(true);
		dinoImage.setFitHeight(60);
		this.dino = dino;
		this.isCircleChosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(30);
		this.getChildren().add(dinoImage);
		this.getChildren().add(circleImage);
		
	}

	public Dinosaur getDino() {
		return dino;
	}
	
	public boolean isCircleChosen() {
		return isCircleChosen;
	}

	public void setCircleChosen(boolean isCircleChosen) {
		this.isCircleChosen = isCircleChosen;
		String imageToSet;
		if(isCircleChosen) {
			imageToSet = circleChosen;
		}
		else {
			imageToSet = circleNotChosen;
		}
		circleImage.setImage(new Image(imageToSet));
	}
	
	
	
}
