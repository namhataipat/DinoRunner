package entity;

import entity.FallingEntity;
import model.Dinosaur;

public class NotYourFood extends FallingEntity {

	public NotYourFood(Dinosaur chosenDino) {
		super(chosenDino.getUrlNotYourFood(), 1, 20);
	}

}
