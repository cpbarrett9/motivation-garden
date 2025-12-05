package model;

import util.Constants;

/**
 * Animal - Represents an animal in the garden
 * 
 * 
 * animal can look for food, move.
 * Animals will avoid plants and alter their direction 
 * of movement upon encountering them.
 * 
 * Supported animal types: cow, dog, cat
 * 
 * Storage: JSON format
 * 
 * @author 
 * @version 1.0
 */
public class Animal extends GardenItem {
    
    /**
     * Type of the animal (cow, dog, or cat)
     */
    protected String type;
    
    /**
     * Default constructor (required for JSON deserialization)
     * 
     * Used by Gson to create an empty Animal object,
     * then fill in the properties from JSON data.
     */
    public Animal() {
        super();
    }
    
    /**
     * Constructor with type and position
     * 
     * @param type Animal type (cow/dog/cat)
     * @param x X-coordinate position in the garden
     * @param y Y-coordinate position in the garden
     */
    public Animal(String type, int x, int y) {
        super(x, y);
        this.type = type;
    }
    
    /**
     * Get the image path for this animal
     * 
     * The image path is constructed based on the animal type.
     * For example: "resources/images/animals/dog.png"
     * 
     * @return String path to the animal's image file
     */
    @Override
    public String getImagePath() {
        return Constants.ANIMAL_IMAGE_DIR + this.type + ".png";
    }
    
    // ========== Getter and Setter ==========
    
    /**
     * Get the animal type
     * 
     * @return String animal type (cow/dog/cat)
     */
    public String getType() {
        return type;
    }
    
    /**
     * Set the animal type
     * 
     * @param type Animal type (cow/dog/cat)
     */
    public void setType(String type) {
        this.type = type;
    }
    
    public int getPrice() {
    	return this.price;
    }
    
    /**
     * Trigger this animal's move method.
     * 
     * @return N/A
     */
    public void moveRandomly() {
    	
    }
    
    /**
     * Returns a string representation of this animal
     * 
     * @return String representation showing type and position
     */
    @Override
    public String toString() {
        return "Animal [type=" + type + 
               ", x=" + positionX + 
               ", y=" + positionY + "]";
    }
}