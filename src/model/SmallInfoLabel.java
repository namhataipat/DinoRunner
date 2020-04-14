package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class SmallInfoLabel extends Label {
	private final static String FONT_PATH = "res/Pomeranian-Regular.ttf";

	public SmallInfoLabel(String text) {
		setPrefSize(170, 50);
		BackgroundImage backgroundImage = new BackgroundImage(
				new Image("/blue_label.png", 150, 50, false, true), 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		setBackground(new Background(backgroundImage));
		setAlignment(Pos.CENTER_LEFT);
		setPadding(new Insets(10));
		setLabelFont();
		setText(text);
	
	}
	
	private void setLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 12));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 15));
		}
	}
	

}
