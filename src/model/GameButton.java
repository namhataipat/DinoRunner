package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.sun.jdi.InvocationException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;



public class GameButton extends Button {

	private final String FONT_PATH = ClassLoader.getSystemResource("Pomeranian-Regular.ttf").toString();
	//private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('res/blue_button01.png');";
	//private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('res/blue_button00.png');";
	
	
	public GameButton(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		this.setButtonReleasedStyle();
		initializeButtonListeners();
	}

	private void setButtonFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 21));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 21));
		}
	}

	private void setButtonPressedStyle() {
		Image pressedBtn = new Image(ClassLoader.getSystemResource("blue_button01.png").toString());
		BackgroundImage pressedBtnBg = new BackgroundImage(pressedBtn, null,null,null,null);
		this.setBackground(new Background(pressedBtnBg));
	//	setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}
	
	private void setButtonReleasedStyle() {
		Image freeBtn = new Image(ClassLoader.getSystemResource("blue_button00.png").toString());
		BackgroundImage freeBtnBg = new BackgroundImage(freeBtn, null,null,null,null);
		this.setBackground(new Background(freeBtnBg));
	//	setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY()-4 );
	}
		
	private void initializeButtonListeners() {
		
		setOnMousePressed(new EventHandler<MouseEvent>() {
			
			
			@Override
			public void handle(MouseEvent e) {
				if (e.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
					
				}
			}
			
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if (e.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				setEffect(new DropShadow());
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				setEffect(null);
				
			}
		});
	}

	}