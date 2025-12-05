package model;

import main.Main;
import util.Constants;

public class Pig extends Animal {

	
	double moveChance;

	
	public Pig(int x, int y) {
		this.positionX = x;
		this.positionY = y;
		this.type = "pig";
		this.moveChance = 0.1;
		this.price = 20;
	}
	
	@Override
    public String getImagePath() {
        return Constants.ANIMAL_IMAGE_DIR + this.type + ".png";
    }
	
	@Override
    public void moveRandomly() {
		double randomNumber = Main.randomDouble(0, 1);
		if ( randomNumber <= moveChance ) {
			
			int moveX = Main.randomInt(-1, 1);
	        if (this.positionX + moveX >= 1 && this.positionX + moveX <= Main.getGardenWidth() ) {
	        	this.positionX += moveX;
	        }
	        int moveY = Main.randomInt(-1, 1);
	        
	        if (this.positionY + moveY >= 1 && this.positionY + moveY <= Main.getGardenHeight() ) {
	        	this.positionY += moveY;
	        }
		}
    }
	
}