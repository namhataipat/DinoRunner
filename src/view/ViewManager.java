package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Dinosaur;
import model.DinoChooser;
import model.GameButton;
import model.InfoLabel;
import model.LeaderBoardEntry;
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
	private Dinosaur chosenDino;

	private List<LeaderBoardEntry> leaders = new ArrayList<>();

	public ViewManager() {
		menuButtons = new ArrayList<GameButton>();
		mainPane = new AnchorPane();
		Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createBackground();
		createLogo();
		createSubScene();

	}

	private void showSubScene(RunningDinoSubScene subScene) {
		if (sceneToHide != null) {
			try {
				updateScore();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		ImageView help = new ImageView(
				new Image(ClassLoader.getSystemResource("help.png").toString(), 450, 450, false, true));
		help.setLayoutX(-5);
		help.setLayoutY(-10);
		helpSubScene.getPane().getChildren().add(help);

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

	private void createScoresSubScene() throws FileNotFoundException {
		this.scoresSubScene = new RunningDinoSubScene();
		mainPane.getChildren().add(scoresSubScene);

		InfoLabel scoresLabel = new InfoLabel("LEADERBOARD");
		scoresLabel.setLayoutX(35);
		scoresLabel.setLayoutY(25);
		ImageView podium = new ImageView(
				new Image(ClassLoader.getSystemResource("podium.png").toString(), 400, 300, false, true));
		podium.setLayoutX(25);
		podium.setLayoutY(100);
		scoresSubScene.getPane().getChildren().addAll(podium, scoresLabel);
		showScore();
	}

	private void getTopScore() throws FileNotFoundException {
		this.leaders = new ArrayList<LeaderBoardEntry>();
		Scanner sc = new Scanner(new File(ClassLoader.getSystemResource("scores.txt").getFile()));
		while (sc.hasNext()) {
			LeaderBoardEntry leaderBoardEntry = new LeaderBoardEntry();
			String read = sc.nextLine();
			String[] splits = read.split("-");
			leaderBoardEntry.setName(splits[0]);
			leaderBoardEntry.setScore(Integer.parseInt(splits[1]));
			leaders.add(leaderBoardEntry);
		}
		sc.close();
		Collections.sort(leaders);
	}

	private void showScore() throws FileNotFoundException {
		getTopScore();
		if (leaders.size() > 2) {
			Text t3 = new Text(leaders.get(2).getName());
			t3.setX(350);
			t3.setY(215);
			t3.resize(300, 300);
			t3.setFont(new Font(ClassLoader.getSystemResource("Pomeranian-Regular.ttf").toString(), 15));
			scoresSubScene.getPane().getChildren().add(t3);
		}
		if (leaders.size() > 1) {
			Text t2 = new Text(leaders.get(1).getName());
			t2.setX(80);
			t2.setY(175);
			t2.resize(300, 300);
			t2.setFont(new Font(ClassLoader.getSystemResource("Pomeranian-Regular.ttf").toString(), 15));
			scoresSubScene.getPane().getChildren().add(t2);
		}
		if (leaders.size() > 0) {
			Text t1 = new Text(leaders.get(0).getName());
			t1.setX(210);
			t1.setY(110);
			t1.resize(300, 300);
			t1.setFont(new Font(ClassLoader.getSystemResource("Pomeranian-Regular.ttf").toString(), 15));
			scoresSubScene.getPane().getChildren().add(t1);
		}
	}

	void updateScore() throws FileNotFoundException {
		createScoresSubScene();
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
		Image backgroundImg = new Image(ClassLoader.getSystemResource("backgroundintro.png").toString(),1024,1024,false,true);
		//Image backgroundImg = new Image("backgroundintro.png", 1024, 1024, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImg, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

	public Stage getMainStage() {
		return mainStage;
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

}
