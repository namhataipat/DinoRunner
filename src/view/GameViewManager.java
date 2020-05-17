package view;

import java.util.Random;

import entity.BigMeteor;
import entity.Dino;
import entity.Meteor;
import entity.NotYourFood;
import entity.YourFood;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import model.Dinosaur;
import model.SmallInfoLabel;

public class GameViewManager {
	public static AnchorPane gamePane;
	private Scene gameScene;
	private static Stage gameStage;
	private static Stage menuStage;

	private static Dinosaur chosenDino;
	private static Dino dinosaur = new Dino();
	private static NotYourFood notYourFood;
	private static YourFood food;
	private static BigMeteor bigMeteors;
	private static Meteor medMeteors;

	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private static AnimationTimer gameTimer;

	private final static String BACKGROUND_IMG = ClassLoader.getSystemResource("backgroundColorGrass.png").toString();

	static Random randomPositionGenerator;
	
	private static SmallInfoLabel pointsLabel;
	private static ImageView[] playerLifes;
	private static int points;
	private static int playerLife;

	private String direction = "left";

	private static AudioClip backgroundMusic;



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
		gameScene = new Scene(gamePane, 800, 600);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		gameStage.setResizable(false);
	}

	public void createNewGame(Stage menuS, Dinosaur chosenD) {
		points = 0;
		chosenDino = chosenD;
		menuStage = menuS;
		menuStage.hide();
		createBackground();
		dinosaur.createDino(chosenDino);
		
		createGameElements(chosenDino);
		createGameLoop();
		backgroundMusic = new AudioClip(ClassLoader.getSystemResource("sound/backGround Sound.wav").toString());
		backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
		backgroundMusic.setVolume(30);
		backgroundMusic.play();
		gameStage.show();
	}

	public static void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(700));
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200)+600));
	}
	
	private void createGameElements(Dinosaur chosenDino) {
		
		food = new YourFood(chosenDino);
		notYourFood = new NotYourFood(chosenDino);
		bigMeteors = new BigMeteor();
		medMeteors = new Meteor();
		
		food.createElement();
		notYourFood.createElement();
		bigMeteors.createElement();
		medMeteors.createElement();
		
		playerLife = 2;
		playerLifes = new ImageView[3];

		for (int i = 0; i < playerLifes.length; i++) {
			playerLifes[i] = new ImageView(chosenDino.getUrlLife());
			playerLifes[i].setLayoutX(640 + (i * 40));
			playerLifes[i].setLayoutY(80);
			gamePane.getChildren().add(playerLifes[i]);

		}
		
		pointsLabel = new SmallInfoLabel("POINTS   00");
		pointsLabel.setLayoutX(610);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);

	}
	
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				moveGameElements();
				checkIfElementsAreBehindTheDinoAndRelocateAll();
				checkIfElementsCollide();
				moveDino();
			}
		};
		gameTimer.start();
	}
	
	private void moveGameElements() {
		food.moveGameElements(9);
		notYourFood.moveGameElements(9);
		bigMeteors.moveGameElements(7);
		medMeteors.moveGameElements(7);
	}
	
	private void checkIfElementsAreBehindTheDinoAndRelocateAll() {
		medMeteors.checkIfElementsAreBehindTheDinoAndRelocate();
		bigMeteors.checkIfElementsAreBehindTheDinoAndRelocate();
		notYourFood.checkIfElementsAreBehindTheDinoAndRelocate();
		food.checkIfElementsAreBehindTheDinoAndRelocate();
	}
	
	private void checkIfElementsCollide() {
		medMeteors.checkIfElementsCollide(dinosaur);
		bigMeteors.checkIfElementsCollide(dinosaur);
		food.checkIfElementsCollide(dinosaur);
		
	}

	private void moveDino() {
		ImageView dino = dinosaur.getDinoImage();
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


	public static void removeLife() {
		if (playerLife >=0){
		gamePane.getChildren().remove(playerLifes[playerLife]);
		playerLife--;
		if (playerLife < 0) {
			backgroundMusic.stop();
			gameStage.close();
			gameTimer.stop();
			menuStage.show();
			showPoints();
			}
		}
	}
	
	private static void showPoints() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("GAME OVER");
		alert.setHeaderText("Dinosaurs went extinct.");
		alert.setContentText("You have eaten "+ points +" piece(s) of food");
		alert.show();
	}
	
	
	public static void increasePoints() {
		points++;
	}
	
	public static int getPlayerLife() {
		return playerLife;
	}
	
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public static int getPoints() {
		return points;
	}

	public static void setPointLabel(String string) {
		pointsLabel.setText(string);
	}

}
