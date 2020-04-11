package view;

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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.DINO;

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

	private GridPane gridPane1;
	private String BACKGROUND_IMG = "backgroundColorGrass.png";

	public GameViewManager() {
		initializeStage();
		createKeyListener();
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
		createGameLoop();
		gameStage.show();
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
				moveDino();
			}
		};
		gameTimer.start();
	}

	private void moveDino() {
		if (isLeftKeyPressed && !isRightKeyPressed) {
			if (dino.getLayoutX() > 10) {
				dino.setLayoutX(dino.getLayoutX() - 3);
			}
		}
		if (isRightKeyPressed && !isLeftKeyPressed) {
			if (dino.getLayoutX() < 650) {
				dino.setLayoutX(dino.getLayoutX() + 3);
			}
		}
	}

	private void createBackground() {
		gridPane1 = new GridPane();
		Image backgroundImg = new Image(BACKGROUND_IMG, 1024, 1024, false, true);
		BackgroundImage backgroundIMG = new BackgroundImage(backgroundImg, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		gridPane1.setBackground(new Background(backgroundIMG));
		gamePane.getChildren().add(gridPane1);
	}

}
