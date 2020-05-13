package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import entity.BigMeteor;
import entity.Dino;
import entity.Meteor;
import entity.NotYourFood;
import entity.YourFood;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
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
	private static AnchorPane gamePane;
	private Scene gameScene;
	private static Stage gameStage;

	private static final int GAME_WIDTH = 800;
	private static final int GAME_HEIGHT = 600;

	private static Stage menuStage;
	private ImageView dino;

	private static Dinosaur chosenDino;
	private static Dino dinosaur = new Dino();
	private static NotYourFood notYourFood;
	private static YourFood food;
	private static BigMeteor bigMeteors = new BigMeteor();
	private static Meteor medMeteors = new Meteor();

	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private static AnimationTimer gameTimer;

	private final static String BACKGROUND_IMG = ClassLoader.getSystemResource("backgroundColorGrass.png").toString();
//	private final static String METEOR_BIG_IMG = ClassLoader.getSystemResource("meteorBrown_big.png").toString();
//	private final static String METEOR_IMG = ClassLoader.getSystemResource("meteorBrown_med.png").toString();

//	private ImageView bigMeteors;
//	private ImageView[] medMeteors;
	Random randomPositionGenerator;

	public static int getPlayerLife() {
		return playerLife;
	}

//	private ImageView[] food;
//	private ImageView notYourFood;
	private static SmallInfoLabel pointsLabel;
	private static ImageView[] playerLifes;
	private static int points;
	private static int playerLife;
//	private final static String C_FOOD_IMAGE = ClassLoader.getSystemResource("cFood.png").toString();
//	private final static String H_FOOD_IMAGE = ClassLoader.getSystemResource("hFood.png").toString();

//	private final static int FOOD_RADIUS = 20;
//	private final static int DINO_RADIUS = 60;
//	private final static int METEOR_RADIUS = 20;
//	private final static int BIG_METEOR_RADIUS = 30;	

	private String direction = "left";

	private static AudioClip backgroundMusic;
//	private static AudioClip getpointSound;
//	private static AudioClip damageSound;

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

	public void createNewGame(Stage menuS, Dinosaur chosenD) {
		chosenDino = chosenD;
		menuStage = menuS;
		menuStage.hide();
		createBackground();
		dinosaur.createDino(chosenD);
		createGameElements(chosenD);
		createGameLoop();
		backgroundMusic = new AudioClip(ClassLoader.getSystemResource("sound/backGround Sound.wav").toString());
		backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
		backgroundMusic.setVolume(30);
		backgroundMusic.play();
		gameStage.show();
	}
	
//	public void createDino(Dinosaur chosenDino) {
	//	dino = new ImageView(chosenDino.getUrlDino());
//		dino.setPreserveRatio(true);
	//	dino.setFitHeight(135);
//		dino.setLayoutX(400);
//		dino.setLayoutY(450);
//	} 
	

	private void createGameElements(Dinosaur chosenDino) {

		playerLife = 2;
		playerLifes = new ImageView[3];

		for (int i = 0; i < playerLifes.length; i++) {
			playerLifes[i] = new ImageView(chosenDino.getUrlLife());
			playerLifes[i].setLayoutX(640 + (i * 40));
			playerLifes[i].setLayoutY(80);
			gamePane.getChildren().add(playerLifes[i]);

		}
		
		food = new YourFood(chosenDino);
		notYourFood = new NotYourFood(chosenDino);

		// String yourFood = null;
		// if(chosenDino.getTypeDino() == "CARNIVORE" ) {
		// yourFood = C_FOOD_IMAGE;
		// notYourFood = new ImageView(H_FOOD_IMAGE);
		// }

		// if(chosenDino.getTypeDino() == "HERBIVORE" ) {
		// yourFood = H_FOOD_IMAGE;
		// notYourFood = new ImageView(C_FOOD_IMAGE);
		// }

		for (int i = 0; i < food.getImageLength(); i++) {
			setNewElementPosition(food.getImageView()[i]);
			gamePane.getChildren().add(food.getImageView()[i]);
		}
		for (int i = 0; i < notYourFood.getImageLength(); i++) {
			setNewElementPosition(notYourFood.getImageView()[i]);
			gamePane.getChildren().add(notYourFood.getImageView()[i]);
		}

		pointsLabel = new SmallInfoLabel("POINTS   00");
		pointsLabel.setLayoutX(610);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);

		for (int i = 0; i < bigMeteors.getImageLength(); i++) {
			setNewElementPosition(bigMeteors.getImageView()[i]);
			gamePane.getChildren().add(bigMeteors.getImageView()[i]);
		}

		for (int i = 0; i < medMeteors.getImageLength(); i++) {
			setNewElementPosition(medMeteors.getImageView()[i]);
			gamePane.getChildren().add(medMeteors.getImageView()[i]);
		}
	}

	private void moveGameElements() {
		for (int i = 0; i < food.getImageLength(); i++) {
			food.getImageView()[i].setLayoutY(food.getImageView()[i].getLayoutY() + 9);
		}
		for (int i = 0; i < notYourFood.getImageLength(); i++) {
			notYourFood.getImageView()[i].setLayoutY(notYourFood.getImageView()[i].getLayoutY() + 9);
		}

		for (int i = 0; i < bigMeteors.getImageLength(); i++) {
			bigMeteors.getImageView()[i].setLayoutY(bigMeteors.getImageView()[i].getLayoutY() + 7);
			bigMeteors.getImageView()[i].setRotate(bigMeteors.getImageView()[i].getRotate() + 4);
		}

		for (int i = 0; i < medMeteors.getImageLength(); i++) {
			medMeteors.getImageView()[i].setLayoutY(medMeteors.getImageView()[i].getLayoutY() + 7);
			medMeteors.getImageView()[i].setRotate(medMeteors.getImageView()[i].getRotate() + 4);
		}
	}

	//private void checkIfElementsAreBehindTheDinoAndRelocate() {

	//	for (int i = 0; i < food.length; i++) {
	//		if (food[i].getLayoutY() > 600) {
	//			setNewElementPosition(food[i]);
	//		}
	//	}

	//	if (notYourFood.getLayoutY() > 600) {
	//		setNewElementPosition(notYourFood);
	//	}

	//	if (bigMeteors.getLayoutY() > 600) {
	//		setNewElementPosition(bigMeteors);
	//	}

	//	for (int i = 0; i < medMeteors.length; i++) {
	//		if (medMeteors[i].getLayoutY() > 600) {
	//			setNewElementPosition(medMeteors[i]);
	//		}
	//	}
	//}

	private void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(700));
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
	}

//	private void createDino(dinosaur chosendino) {
//		dino = new ImageView(chosenDino.getUrlDino());
//		dino.setPreserveRatio(true);
//		dino.setFitHeight(135);
//		dino.setLayoutX(GAME_WIDTH / 2);
//		dino.setLayoutY(GAME_HEIGHT - 150);
//		gamePane.getChildren().add(dino);
//	}

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

	public static void increasePoints() {
		points++;
	}

	public static void setPointLabel(String s) {
		pointsLabel.setText(s);
	}

	// private void checkIfElementsCollide() {
//		for(int i=0; i<food.length; i++) {
	// if (DINO_RADIUS+FOOD_RADIUS >calculateDistance(dino.getLayoutX()+40,
	// food[i].getLayoutX()+20,
//					dino.getLayoutY()+40, food[i].getLayoutY()+20)){

//				getpointSound = new AudioClip(ClassLoader.getSystemResource("sound/getpoint.wav").toString());
//				getpointSound.play();
//				setNewElementPosition(food[i]);

//				points++;
//				String textToSet = "POINTS   ";
//				if(points<10) {
//					textToSet = textToSet +"0";
///				}
//				pointsLabel.setText(textToSet+ points);
//			}

//		}
//		damageSound = new AudioClip(ClassLoader.getSystemResource("sound/damage.wav").toString());
//		if (BIG_METEOR_RADIUS+DINO_RADIUS  > calculateDistance(dino.getLayoutX()+20,
//				bigMeteors.getLayoutX()+20 , dino.getLayoutY()+40, bigMeteors.getLayoutY()+20)) {
//			damageSound.play();
//			removeLife();
//			if (playerLife >= 0) {
//				removeLife();
//			}
//			setNewElementPosition(bigMeteors);
//		}

//		for(int i=0; i<medMeteors.length; i++) {
//			if (METEOR_RADIUS+DINO_RADIUS  > calculateDistance(dino.getLayoutX()+20,
//					medMeteors[i].getLayoutX()+20 , dino.getLayoutY()+40, medMeteors[i].getLayoutY()+20)) {
//				damageSound.play();
//				removeLife();
//				setNewElementPosition(medMeteors[i]);
//			}
//		}

	// }

	public static void removeLife() {

		gamePane.getChildren().remove(playerLifes[playerLife]);
		playerLife--;
		if (playerLife < 0) {
			backgroundMusic.stop();
			gameStage.close();
			gameTimer.stop();
			menuStage.show();
			createTextInputDialog();
		}
	}

	private static void createTextInputDialog() {
		TextInputDialog td = new TextInputDialog("Enter your name");
		td.setTitle("GAME OVER");
		td.setHeaderText("Dinosaurs went extinct.");
		td.setOnHidden(e -> saveScores(td.getResult()));
		td.show();
	}

	private static void saveScores(String playerName) {
		try {
			FileWriter writer = new FileWriter(new File(ClassLoader.getSystemResource("scores.txt").getFile()), true);
			writer.write(playerName.trim() + "-" + getPoints() + "\n");
			// writer.write(this.getPoints() + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("IOException in SaveScores");
		}
	}

	public static int getPoints() {
		return points;
	}

//	private double calculateDistance(double x1, double x2, double y1, double y2) {
//		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
//	}

}
