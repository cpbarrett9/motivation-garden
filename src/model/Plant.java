package model;

import util.Constants;

/**
 * 
 * 
 * @author 
 * @version 1.0
 * 
 */

public class Plant extends GardenItem {
	
	protected String type;



	public Plant() {
		super();
	}
	
	public Plant(String type,int x,int y) {
		super(x,y);
		this.type=type;	
	}
	
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
	
	
	  @Override
	    public String getImagePath() {
	        return Constants.PLANT_IMAGE_DIR + type + ".png";
	    }
	

	    public String toString() {
	        return "Plant [type=" + type + 
	               ", x=" + positionX + 
	               ", y=" + positionY + "]";
	    }


}
