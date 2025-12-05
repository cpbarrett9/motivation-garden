package model;

import main.Main;
import util.Constants;

public class Chicken extends Animal {
	

	double moveChance;

	
	public Chicken(int x, int y) {
		this.positionX = x;
		this.positionY = y;
		this.type = "chicken";
		this.moveChance = 0.2;
		this.price = 15;
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
