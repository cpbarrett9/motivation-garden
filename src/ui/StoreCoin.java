package ui;


import ecs100.UI;
import manager.StoreManager;

/* ====================================================================================================================	*/
/**
 * 	StoreCoin: 			Coin and money count to be drawn on screen. Used by GUI.
 *
 * 	@version  			1.0
 * 	@since    			1.0
 */
/* ====================================================================================================================	*/

public class StoreCoin {

	// Image file paths:
	private static String sprite = "resources/images/ui/coin.png";
	private static String spriteRed = "resources/images/ui/coin_red.png"; // <- The coin turns red when the user doesn't have enough money
	
	public StoreCoin() {}
	
/* ====================================================================================================================	*/

	/// drawCoin:
	/** Draws the coin and a money count in the GUI. Called by drawWorld()
	*  
	*	@param int			x: X position to draw the coin
	*	@param int			y: Y position to draw the coin
	*	@return ->			N/A.	
	*																														*/
	public static void drawCoin(int x, int y) {
		UI.drawImage(sprite, x, y);
		UI.setFontSize(30);
		UI.drawString("x "+StoreManager.getMoney(), x+70, y+40);
	}
	
	/// turnRed:
	/** Briefly sets the image to a red version of the coin, then changes it back. Called when the user doesn't have enough money to buy an item.
	*  
	*	@return ->			N/A.	
	*																														*/
	public static void turnRed() throws InterruptedException {
		String tempSprite = sprite; // <- Remember the old image path so it can be set back
		sprite = spriteRed;
		Thread.sleep(500); // <- Wait for half a second
		sprite = tempSprite; // <- Then turn back to the normal sprite
	}
	
}
