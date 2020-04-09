package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import model.GameButton;

public class ViewManager {
	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private AnchorPane mainPane;
	private Stage mainStage;
	private Scene mainScene;
	
	private final static int MENU_BUTTONS_START_X = 50;
	private final static int MENU_BUTTONS_START_Y = 125;
	
	List<GameButton> menuButtons;
	
	
	public ViewManager() {
		menuButtons = new ArrayList<GameButton>();
		mainPane = new AnchorPane();
		Scene mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createBackground();
	}
	private void addMenuButton(GameButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size()*75);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	private void createButtons() {
		createStartButton();
		createScoresButton();
		createHelpButton();
		createCreditButton();
		createExitButton();
	}
	
	private void createStartButton() {
		GameButton startButton = new GameButton("PLAY");
		addMenuButton(startButton);
	}
	
	private void createScoresButton() {
		GameButton scoresButton = new GameButton("SCORES");
		addMenuButton(scoresButton);
	}
	private void createHelpButton() {
		GameButton helpButton = new GameButton("HELP");
		addMenuButton(helpButton);
	}
	private void createCreditButton() {
		GameButton creditButton = new GameButton("CREDITS");
		addMenuButton(creditButton);
	}
	private void createExitButton() {
		GameButton exitButton = new GameButton("EXIT");
		addMenuButton(exitButton);
	}
	
	private void createBackground() {
		Image backgroundImg = new Image("backgroundintro.png",256,256,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImg, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}
	public static int getHeight() {
		return HEIGHT;
	}
	public static int getWidth() {
		return WIDTH;
	}
	public AnchorPane getMainPane() {
		return mainPane;
	}
	public Stage getMainStage() {
		return mainStage;
	}
	public Scene getMainScene() {
		return mainScene;
	}
	public static int getMenuButtonsStartX() {
		return MENU_BUTTONS_START_X;
	}
	public static int getMenuButtonsStartY() {
		return MENU_BUTTONS_START_Y;
	}
	public List<GameButton> getMenuButtons() {
		return menuButtons;
	}
	
}
