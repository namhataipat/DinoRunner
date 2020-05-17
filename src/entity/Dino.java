package entity;

import entity.base.Entity;
import javafx.scene.image.ImageView;
import model.Dinosaur;
import view.GameViewManager;

public class Dino extends Entity{
	
	protected ImageView dino;
	
	public Dino() {
		this.radius = 60;
	}

	public void createDino(Dinosaur chosenDino) {
		dino = new ImageView(chosenDino.getUrlDino());
		dino.setPreserveRatio(true);
		dino.setFitHeight(135);
		dino.setLayoutX(400);
		dino.setLayoutY(450);
		GameViewManager.gamePane.getChildren().add(dino);
	} 
	
	public ImageView getDinoImage() {
		return dino;
	}
	

}
