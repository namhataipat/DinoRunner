package entity.base;

import java.util.Random;

import entity.Dino;
import javafx.scene.image.ImageView;

public class FallingEntity extends Entity  {

	protected ImageView[] imageView;
	protected int imageLength;
	protected String imageURL;
	Random randomPositionGenerator;

	
	public FallingEntity(String imageURL, int imageLength ,int radius) {
		super(); 
		this.imageURL = imageURL;
		this.imageView = new ImageView[imageLength];
		for (int i=0; i<imageLength; i++) {
			this.imageView[i] = new ImageView(imageURL);
		}
		this.radius = radius;
	}

	protected double calculateDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	
	protected void moveGameElements(int n) {
		for(int i=0; i<imageLength; i++) {
			imageView[i].setLayoutY(imageView[i].getLayoutY()+n);
		}
	}
	
	protected boolean isElementsCollide(Dino dino) {
		for(int i=0; i<imageView.length; i++) {
		if (dino.getRadius()+this.getRadius() >calculateDistance(dino.getDinoImage().getLayoutX()+20, this.getImageView()[i].getLayoutX()+20, 
				dino.getDinoImage().getLayoutY()+40, this.getImageView()[i].getLayoutY()+20)) 
			return true;
		}
		return false;
		
	}
	
	public void checkIfElementsAreBehindTheDinoAndRelocate() {
		for(int i=0; i<imageView.length; i++) {
			if(imageView[i].getLayoutY() > 600) 
				this.setNewElementPosition(imageView[i]);
		}
	}

	public ImageView[] getImageView() {
		return imageView;
	}
	
	public int getImageLength() {
		return imageLength;
	}

	public void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(700));
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200)+600));
	}


	
	
	

}
