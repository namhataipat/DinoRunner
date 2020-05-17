package view;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Dinosaur;
import model.DinoChooser;
import model.GameButton;
import model.InfoLabel;
import model.RunningDinoSubScene;

public class ViewManager {

	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private AnchorPane mainPane;
	private Stage mainStage;

	private final static int MENU_BUTTONS_START_X = 50;
	private final static int MENU_BUTTONS_START_Y = 170;

	private RunningDinoSubScene dinoChooserSubScene;
	private RunningDinoSubScene helpSubScene;
	private RunningDinoSubScene creditSubScene;
	private RunningDinoSubScene sceneToHide;

	List<GameButton> menuButtons;
	List<DinoChooser> dinoList;
	private Dinosaur chosenDino;
	

	public ViewManager() {
		menuButtons = new ArrayList<GameButton>();
		mainPane = new AnchorPane();
		Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		
		createBackground();
		createLogo();
		createSubScene();
		createButtons();

	}

	private void showSubScene(RunningDinoSubScene subScene) {
		if (sceneToHide != null) {
			sceneToHide.moveSubscene();
		}
		subScene.moveSubscene();
		sceneToHide = subScene;
	}

	private void createSubScene() {
		dinoChooserSubScene = new RunningDinoSubScene();
		mainPane.getChildren().add(dinoChooserSubScene);
		
		helpSubScene = new RunningDinoSubScene();
		mainPane.getChildren().add(helpSubScene);
		addImageToSubscene("howtoplay.png", helpSubScene);
		
		creditSubScene = new RunningDinoSubScene();
		mainPane.getChildren().add(creditSubScene);
		addImageToSubscene("credit.png", creditSubScene);
		
		createDinoChooserSubScene();
	}
	
	
	private void addImageToSubscene(String fileName, RunningDinoSubScene subscene) {
			
		ImageView image = new ImageView(
				new Image(ClassLoader.getSystemResource(fileName).toString(), 450, 400, false, true));
		image.setLayoutX(0);
		image.setLayoutY(-5);
		subscene.getPane().getChildren().add(image);
	}

	private void createDinoChooserSubScene() {
		this.dinoChooserSubScene = new RunningDinoSubScene();
		mainPane.getChildren().add(dinoChooserSubScene);

		InfoLabel chooseDinoLabel = new InfoLabel("CHOOSE YOUR DINO");
		chooseDinoLabel.setFont(Font.loadFont(ClassLoader.getSystemResource("Pomeranian-Regular.ttf").toString(), 20));
		chooseDinoLabel.setLayoutX(35);
		chooseDinoLabel.setLayoutY(25);
		dinoChooserSubScene.getPane().getChildren().add(chooseDinoLabel);
		dinoChooserSubScene.getPane().getChildren().add(createDinoToChoose());
		dinoChooserSubScene.getPane().getChildren().add(createButtonToStart());
	}
	
	
	private void createButtons() {
		createAButton("PLAY", dinoChooserSubScene);
		createAButton("HELP", helpSubScene);
		createAButton("CREDIT", creditSubScene);
		createExitButton();
		
	}
	
	private void createAButton(String buttonName, RunningDinoSubScene subsceneName) {
		GameButton button = new GameButton(buttonName);
		addMenuButton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent arg0) {
				showSubScene(subsceneName);
			}
		});
	}
	
	private void createExitButton() {
		GameButton exitButton = new GameButton("EXIT");
		addMenuButton(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				mainStage.close();
			}
		});
	}
	
	private HBox createDinoToChoose() {
		HBox box = new HBox();
		box.setSpacing(30);
		dinoList = new ArrayList<>();
		for (Dinosaur dino : Dinosaur.values()) {
			DinoChooser dinoToChoose = new DinoChooser(dino);
			dinoList.add(dinoToChoose);
			box.getChildren().add(dinoToChoose);
			dinoToChoose.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent e) {
					for (DinoChooser dino : dinoList) {
						dino.setCircleChosen(false);
					}
					dinoToChoose.setCircleChosen(true);
					chosenDino = dinoToChoose.getDino();
				}
			});
		}
		box.setLayoutX(64);
		box.setLayoutY(150);
		return box;
	}

	private GameButton createButtonToStart() {
		GameButton startButton = new GameButton("START");
		startButton.setLayoutX(135);
		startButton.setLayoutY(300);

		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// TODO Auto-generated method stub
				if (chosenDino != null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.createNewGame(mainStage, chosenDino);
				}
			}
		});

		return startButton;
	}

	private void addMenuButton(GameButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 80);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}


	

	private void createBackground() {
		Image backgroundImg = new Image(ClassLoader.getSystemResource("backgroundintro.png").toString(),1024,1024,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImg, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

	
	private void createLogo() {
		ImageView logo = new ImageView(ClassLoader.getSystemResource("logo1.png").toString());
		logo.setLayoutX(270);
		logo.setLayoutY(20);
		logo.setPreserveRatio(true);
		logo.setFitHeight(200);
		logo.setFitWidth(500);

		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				logo.setEffect(new DropShadow());
			}
		});

		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				logo.setEffect(null);
			}
		});

		mainPane.getChildren().add(logo);
	}
	
	public Stage getMainStage() {
		return mainStage;
	}


}
