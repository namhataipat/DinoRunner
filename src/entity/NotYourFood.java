package entity;

import entity.base.FallingEntity;
import model.Dinosaur;

public class NotYourFood extends FallingEntity {

	public NotYourFood(Dinosaur chosenDino) {
		super(chosenDino.getUrlFood(), 1, 20);
	}

}
