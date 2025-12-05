package model;

import main.Main;
import util.Constants;

public class Cow extends Animal {
	



	double moveChance;

	
	public Cow(int x, int y) {
		this.positionX = x;
		this.positionY = y;
		this.type = "cow";
		this.moveChance = 0.05;
		this.price = 25;
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
