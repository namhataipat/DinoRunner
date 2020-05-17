package entity;

import java.util.Random;

import entity.base.Entity;
import javafx.scene.image.ImageView;
import view.GameViewManager;

public class FallingEntity extends Entity  {

	protected ImageView[] imageView;
	protected int imageLength;
	protected String imageURL;
	Random randomPositionGenerator;

	
	public FallingEntity(String imageURL, int imageLength ,int radius) {
		super(); 
		this.imageURL = imageURL;
		this.imageLength = imageLength;
		this.imageView = new ImageView[imageLength];
		this.radius = radius;
	}
	
	public void createElement() {
		for (int i=0; i<imageLength; i++) {
			this.imageView[i] = new ImageView(imageURL);
			GameViewManager.setNewElementPosition(imageView[i]);
			GameViewManager.gamePane.getChildren().add(imageView[i]);
		}
	}


	protected double calculateDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	
	public void moveGameElements(int n) {
		for(int i=0; i<imageLength; i++) {
			imageView[i].setLayoutY(imageView[i].getLayoutY()+n);
			if ((this instanceof BigMeteor) || (this instanceof Meteor)) {
						imageView[i].setRotate(imageView[i].getLayoutY()+4);
					}
				}
			}
	
	protected int whichElementsCollide(Dino dino) {
		for(int i=0; i<imageView.length; i++) {
		if (dino.getRadius()+this.getRadius() >calculateDistance(dino.getDinoImage().getLayoutX()+20, this.getImageView()[i].getLayoutX()+20, 
				dino.getDinoImage().getLayoutY()+40, this.getImageView()[i].getLayoutY()+20)) 
			return i;
		}
		return -1;
		
	}
	
	public void checkIfElementsAreBehindTheDinoAndRelocate() {
		for(int i=0; i<imageView.length; i++) {
			if(imageView[i].getLayoutY() > 600) 
				GameViewManager.setNewElementPosition(imageView[i]);
		}
	}
	
	
	public void setImageView(int i) {
		this.imageView[i] = new ImageView(this.getImageURL());
	}


	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


	public void setRandomPositionGenerator(Random randomPositionGenerator) {
		this.randomPositionGenerator = randomPositionGenerator;
	}


	public String getImageURL() {
		return imageURL;
	}

	public ImageView[] getImageView() {
		return imageView;
	}
	
	public int getImageLength() {
		return imageLength;
	}
	

}
