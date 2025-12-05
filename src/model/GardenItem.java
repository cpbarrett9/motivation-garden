package model;

/**
 * GardenItem - Abstract base class for all garden items
 * 
 * This class serves as the parent class for all items that can be placed
 * in the garden, including plants, animals, and food.
 * 
 * All subclasses must implement the getImagePath() method to provide
 * their specific image resource path.
 * 
 * 
 * @author The corner
 * @version 1.0
 */
public abstract class GardenItem {
    
    /**
     * How much should the item cost?
     */
    protected int price;
	
    /**
     * X-coordinate position in the garden
     */
    protected int positionX;
    
    /**
     * Y-coordinate position in the garden
     */
    protected int positionY;
    
    /**
     * Image path for this item
     */
    protected String imagePath;
    
    /**
     * Default constructor (required for JSON deserialization)
     */
    public GardenItem() {
        // Empty constructor for JSON
    }
    
    public void moveRandomly() {
    	
    }
    
    /**
     * Constructor to initialize a garden item with position
     * 
     * @param x X-coordinate position in the garden
     * @param y Y-coordinate position in the garden
     */
    public GardenItem(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }
    
    /**
     * Abstract method to get the image path for this item
     * 
     * Each subclass must implement this method to return the appropriate
     * image path based on its type.
     * 
     * @return String path to the image file
     */
    public String getImagePath() {
    	
    	return this.imagePath;
    	
    }
    
    // ========== Getters and Setters ==========
    
    /**
     * Get the X-coordinate position
     * 
     * @return int X-coordinate
     */
    public int getPositionX() {
        return positionX;
    }
    
    /**
     * Set the X-coordinate position
     * 
     * @param x New X-coordinate
     */
    public void setPositionX(int x) {
        this.positionX = x;
    }
    
    /**
     * Get the Y-coordinate position
     * 
     * @return int Y-coordinate
     */
    public int getPositionY() {
        return positionY;
    }
    
    /**
     * Get the item's price.
     * 
     * @return int price
     */
    public int getPrice() {
        return this.price;
    }
    
    /**
     * Set the Y-coordinate position
     * 
     * @param y New Y-coordinate
     */
    public void setPositionY(int y) {
        this.positionY = y;
    }
    
    /**
     * Set both X and Y coordinates simultaneously
     * 
     * @param x New X-coordinate
     * @param y New Y-coordinate
     */
    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }
    
    /**
     * Returns a string representation of this garden item
     * Useful for debugging purposes
     * 
     * @return String representation showing class name and position
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + 
               " [x=" + positionX + ", y=" + positionY + "]";
    }
}