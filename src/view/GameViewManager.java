package view;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import model.DINO;
import model.SmallInfoLabel;

public class GameViewManager {
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;

	private static final int GAME_WIDTH = 800;
	private static final int GAME_HEIGHT = 600;

	private Stage menuStage;
	private ImageView dino;

	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private AnimationTimer gameTimer;

	private final static String BACKGROUND_IMG = "backgroundColorGrass.png";
	
	private final static String METEOR_BIG_IMG = "meteorBrown_big.png";
	private final static String METEOR_IMG = "meteorBrown_med.png";
	
	private ImageView bigMeteors;
	private ImageView[] medMeteors;
	Random randomPositionGenerator;
	

	private ImageView[] food;
	private ImageView notYourFood;
	private SmallInfoLabel pointsLabel;
	private ImageView[] playerLifes;
	private int points;
	private int playerLife;
	private final static String C_FOOD_IMAGE = "cFood.png";
	private final static String H_FOOD_IMAGE = "hFood.png";
	
	private final static int FOOD_RADIUS = 20;
	private final static int DINO_RADIUS = 60;
	private final static int METEOR_RADIUS = 20;
	private final static int BIG_METEOR_RADIUS = 30;
	
	private String direction = "left";

	

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public GameViewManager() {
		initializeStage();
		createKeyListener();
		randomPositionGenerator = new Random();
	}

	private void createKeyListener() {

		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				} else if (e.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = true;
				}
			}
		});

		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				} else if (e.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = false;
				}
			}
		});
	}

	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
	}

	public void createNewGame(Stage menuStage, DINO chosenDino) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		createDino(chosenDino);
		createGameElements(chosenDino);
		createGameLoop();
		gameStage.show();
	}
	
	private void createGameElements(DINO chosenDino) {
		
		playerLife = 2;
		playerLifes = new ImageView[3];
		
		for(int i=0; i<playerLifes.length; i++) {
			playerLifes[i] = new ImageView(chosenDino.getUrlLife());
			playerLifes[i].setLayoutX(640 + (i*40));
			playerLifes[i].setLayoutY(80);
			gamePane.getChildren().add(playerLifes[i]);
			
		}
		
		String yourFood = null;
		if(chosenDino.getTypeDino() == "CARNIVORE" ) {
			yourFood = C_FOOD_IMAGE;
			notYourFood = new ImageView(H_FOOD_IMAGE);
		}
		
		if(chosenDino.getTypeDino() == "HERBIVORE" ) {
			yourFood = H_FOOD_IMAGE;
			notYourFood = new ImageView(C_FOOD_IMAGE);
		}
		
		food = new ImageView[2];
		for(int i=0; i<food.length; i++) {
			food[i] = new ImageView(yourFood);
			setNewElementPosition(food[i]);
			gamePane.getChildren().add(food[i]);
		}
			
		setNewElementPosition(notYourFood);
		gamePane.getChildren().add(notYourFood);
		
		pointsLabel = new SmallInfoLabel("POINTS   00");
		pointsLabel.setLayoutX(610);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);
		
		bigMeteors = new ImageView(METEOR_BIG_IMG);
			setNewElementPosition(bigMeteors);
			gamePane.getChildren().add(bigMeteors);
		
		medMeteors = new ImageView[5];
		for(int i=0; i<medMeteors.length; i++) {
			medMeteors[i] = new ImageView(METEOR_IMG);
			setNewElementPosition(medMeteors[i]);
			gamePane.getChildren().add(medMeteors[i]);
		}
	}
	
	private void moveGameElements() {
		
		for(int i=0; i<food.length; i++) {
			food[i].setLayoutY(food[i].getLayoutY()+9);
		}
		
		notYourFood.setLayoutY(notYourFood.getLayoutY()+9);
		
		bigMeteors.setLayoutY(bigMeteors.getLayoutY()+7);
		bigMeteors.setRotate(bigMeteors.getRotate()+4);
		
		
		for(int i=0; i<medMeteors.length; i++) {
			medMeteors[i].setLayoutY(medMeteors[i].getLayoutY()+7);
			medMeteors[i].setRotate(medMeteors[i].getRotate()+4);
		}
	}
	
	private void checkIfElementsAreBehindTheDinoAndRelocate() {
		
		for(int i=0; i<food.length; i++) {
		if(food[i].getLayoutY() > 600) {
			setNewElementPosition(food[i]);
			}
		}
		
		if(notYourFood.getLayoutY() > 1200) {
			setNewElementPosition(notYourFood);
		}
	
		if(bigMeteors.getLayoutY() > 600) {
			setNewElementPosition(bigMeteors);
		}

		for(int i=0; i<medMeteors.length; i++) {
			if(medMeteors[i].getLayoutY() > 600) {
				setNewElementPosition(medMeteors[i]);
			}
		}
	}

	private void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(700));
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200)+600));
	}

	private void createDino(DINO chosenDino) {
		dino = new ImageView(chosenDino.getUrlDino());
		dino.setPreserveRatio(true);
		dino.setFitHeight(135);
		dino.setLayoutX(GAME_WIDTH / 2);
		dino.setLayoutY(GAME_HEIGHT - 150);
		gamePane.getChildren().add(dino);
	}

	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				moveGameElements();
				checkIfElementsAreBehindTheDinoAndRelocate();
				checkIfElementsCollide();
				moveDino();
			}
		};
		gameTimer.start();
	}

	private void moveDino() {
		if (isLeftKeyPressed && !isRightKeyPressed) {
			if (this.getDirection().equals("right")) {
				dino.setScaleX(1);
			}
			if (dino.getLayoutX() > 10) {
				dino.setLayoutX(dino.getLayoutX() - 5);
				
			}
			this.setDirection("left");
		}
		if (isRightKeyPressed && !isLeftKeyPressed) {
			if (this.getDirection().equals("left")) {
				dino.setScaleX(-1);
			}
			if (dino.getLayoutX() < 650) {
				dino.setLayoutX(dino.getLayoutX() + 5);
			}
			this.setDirection("right");
		}
	}
	

	private void createBackground() {
		Image backgroundImg = new Image(BACKGROUND_IMG, 600, 800, false, true);
		BackgroundImage backgroundIMG = new BackgroundImage(backgroundImg, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		gamePane.setBackground(new Background(backgroundIMG));
	}
	
	private void checkIfElementsCollide() {
		for(int i=0; i<food.length; i++) {
			if (DINO_RADIUS+FOOD_RADIUS >calculateDistance(dino.getLayoutX()+40, food[i].getLayoutX()+20, 
					dino.getLayoutY()+40, food[i].getLayoutY()+20)){
						setNewElementPosition(food[i]);
						
						points++;
						String textToSet = "POINTS   ";
						if(points<10) {
							textToSet = textToSet +"0";
						}
						pointsLabel.setText(textToSet+ points);
					}
			
		}
		
		if (BIG_METEOR_RADIUS+DINO_RADIUS  > calculateDistance(dino.getLayoutX()+20,
				bigMeteors.getLayoutX()+20 , dino.getLayoutY()+40, bigMeteors.getLayoutY()+20)) {
			removeLife();
			removeLife();
			setNewElementPosition(bigMeteors);
		}
		
		for(int i=0; i<medMeteors.length; i++) {
			if (METEOR_RADIUS+DINO_RADIUS  > calculateDistance(dino.getLayoutX()+20,
					medMeteors[i].getLayoutX()+20 , dino.getLayoutY()+40, medMeteors[i].getLayoutY()+20)) {
				removeLife();
				setNewElementPosition(medMeteors[i]);
			}
		}
		
 	}
	
	private void removeLife() {
		
		gamePane.getChildren().remove(playerLifes[playerLife]);
		playerLife--;
		if(playerLife<0) {
			gameStage.close();
			gameTimer.stop();
			menuStage.show();
		}
	}

	private double calculateDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
}
