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
import javafx.stage.Stage;
import model.DINO;
import model.DinoChooser;
import model.GameButton;
import model.InfoLabel;
import model.RunningDinoSubScene;


public class ViewManager {
	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private AnchorPane mainPane;
	private Stage mainStage;
	private Scene mainScene;
	
	private final static int MENU_BUTTONS_START_X = 50;
	private final static int MENU_BUTTONS_START_Y = 170;
	
	private RunningDinoSubScene dinoChooserSubScene;
	private RunningDinoSubScene scoresSubScene;
	private RunningDinoSubScene helpSubScene;
	private RunningDinoSubScene creditSubScene;
	
	private RunningDinoSubScene sceneToHide;
	
	List<GameButton> menuButtons;
	List<DinoChooser> dinoList;
	private DINO chosenDino;
	
	
	public ViewManager() {
		menuButtons = new ArrayList<GameButton>();
		mainPane = new AnchorPane();
		Scene mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createBackground();
		createLogo();
		createSubScene();
		
	}
	
	private void showSubScene(RunningDinoSubScene subScene) {
		if(sceneToHide != null) {
			sceneToHide.moveSubscene();
		}
		subScene.moveSubscene();
		sceneToHide = subScene;
	}
	
	private void createSubScene() {
		dinoChooserSubScene = new RunningDinoSubScene();
		mainPane.getChildren().add(dinoChooserSubScene);
		
		scoresSubScene = new RunningDinoSubScene();
		mainPane.getChildren().add(scoresSubScene);
		
		helpSubScene = new RunningDinoSubScene();
		mainPane.getChildren().add(helpSubScene);
		
		creditSubScene = new RunningDinoSubScene();
		mainPane.getChildren().add(creditSubScene);
		
		createDinoChooserSubScene();
	}
	
	private void createDinoChooserSubScene() {
		this.dinoChooserSubScene = new RunningDinoSubScene();
		mainPane.getChildren().add(dinoChooserSubScene);
		
		InfoLabel chooseDinoLabel = new InfoLabel("CHOOSE YOUR DINO");
		chooseDinoLabel.setLayoutX(35);
		chooseDinoLabel.setLayoutY(25);
		dinoChooserSubScene.getPane().getChildren().add(chooseDinoLabel);
		dinoChooserSubScene.getPane().getChildren().add(createDinoToChoose());
		dinoChooserSubScene.getPane().getChildren().add(createButtonToStart());
	}
	
	private HBox createDinoToChoose() {
		HBox box = new HBox();
		box.setSpacing(30);
		dinoList = new ArrayList<>();
		for (DINO dino : DINO.values()) {
			DinoChooser dinoToChoose = new DinoChooser(dino);
			dinoList.add(dinoToChoose);
			box.getChildren().add(dinoToChoose);
			dinoToChoose.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent e) {
					for (DinoChooser dino: dinoList) {
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
		button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size()*80);
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
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				showSubScene(dinoChooserSubScene);
			}
		});
	}
	
	private void createScoresButton() {
		GameButton scoresButton = new GameButton("SCORES");
		addMenuButton(scoresButton);
		scoresButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				showSubScene(scoresSubScene);
			}
		});
	}
	
	private void createHelpButton() {
		GameButton helpButton = new GameButton("HELP");
		addMenuButton(helpButton);
		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				showSubScene(helpSubScene);
				
			}
		});
	}
	
	private void createCreditButton() {
		GameButton creditButton = new GameButton("CREDITS");
		addMenuButton(creditButton);
		creditButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
			showSubScene(creditSubScene);
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
	
	private void createBackground() {
		Image backgroundImg = new Image("backgroundintro.png",1024,1024,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImg, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}
//	public static int getHeight() {
//		return HEIGHT;
//	}
//	public static int getWidth() {
//		return WIDTH;
//	}
//	public AnchorPane getMainPane() {
//		return mainPane;
//	}
	public Stage getMainStage() {
		return mainStage;
	}
//	public Scene getMainScene() {
//		return mainScene;
//	}
//	public static int getMenuButtonsStartX() {
//		return MENU_BUTTONS_START_X;
//	}
//	public static int getMenuButtonsStartY() {
//		return MENU_BUTTONS_START_Y;
//	}
//	public List<GameButton> getMenuButtons() {
//		return menuButtons;
//	}
	
	private void createLogo() {
		ImageView logo = new ImageView("logo1.png");
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
	
}
