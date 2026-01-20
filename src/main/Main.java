package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import datacontroller.CoinDataController;
import datacontroller.GardenDataController;
import datacontroller.TaskDataController;
import ecs100.*;
import manager.StoreManager;

import manager.TaskManager;
import model.*;

import ui.GUI;
import ui.TodoPanel;
import util.Constants;

/* ====================================================================================================================	*/
/**
 * Main: Launches program and handles logic shared across classes.
 *
 * @version 1.0
 * @since 1.0
 * 
 */

public class Main {
	
	// Collections:
//	static ArrayList<Animal> animals = new ArrayList<>(); // <- Animals and Plants are separated so they can be read by
	static List<Cow> cows = new ArrayList<>();
	static List<Chicken> chickens = new ArrayList<>();
	static List<Pig> pigs = new ArrayList<>(); // JSON files
	static List<Plant> plants = new ArrayList<>();

	static ArrayList<GardenItem> newItems = new ArrayList<>(); // <- Any new items are added to this list first,
																// then added to the plants and animals lists at the
																// *end* of
																// the drawWorld method. This is avoid concurrent
																// modification.
	// Class-Wide variables:
	private static int GARDEN_WIDTH = 16; // <- Width/height of the garden. Used by GUI and by moving animals to find
	private static int GARDEN_HEIGHT = 16; // out were they're allowed to go.

	public Main() throws InterruptedException {

		GUI gui = new GUI(); // <- Create GUI

		// add default garden items
		addDefaultItems();

		// load saved files gardenitems & tasks & coins
		loadJson();

		StoreManager.init(); // sync StoreManager.money with TaskManager.money

		GUI.drawWorld();

		// Launch To-Do list window (shows the same coin balance)
		SwingUtilities.invokeLater(() -> {
			JFrame todoFrame = new JFrame("To-Do List with clear");
			todoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			todoFrame.add(new TodoPanel());
			todoFrame.setSize(400, 500);
			todoFrame.setLocation(50, 50); // separate from farm window
			todoFrame.setVisible(true);
		});

		updateWorld(12); // <- Start updating farm continuously.
	}

	public static void addDefaultItems() {

		// STARTING ITEMS:
		plants.add(new Tree(randomInt(1, GARDEN_WIDTH), randomInt(1, GARDEN_HEIGHT))); // <- Spawn two trees somewhere
																						// random on the grid.
		plants.add(new Tree(randomInt(1, GARDEN_WIDTH), randomInt(1, GARDEN_HEIGHT)));
		plants.add(new Flower(randomInt(1, GARDEN_WIDTH), randomInt(1, GARDEN_HEIGHT))); // <- Spawn three flowers
																							// somewhere random on the
																							// grid.
		plants.add(new Flower(randomInt(1, GARDEN_WIDTH), randomInt(1, GARDEN_HEIGHT)));
		plants.add(new Flower(randomInt(1, GARDEN_WIDTH), randomInt(1, GARDEN_HEIGHT)));
		// Randomly spawn either a cow, pig, or chicken to start:
		int randomNumber = randomInt(1, 3);
		switch (randomNumber) {
		case 1:
			cows.add(new Cow(randomInt(1, GARDEN_WIDTH), randomInt(1, GARDEN_HEIGHT)));
			break;
		case 2:
			pigs.add(new Pig(randomInt(1, GARDEN_WIDTH), randomInt(1, GARDEN_HEIGHT)));
			break;
		case 3:
			chickens.add(new Chicken(randomInt(1, GARDEN_WIDTH), randomInt(1, GARDEN_HEIGHT)));
			break;
		default:
			break;
		}
	}

	public static void loadJson() {

		if (fileExists(Constants.COW_DATA_DIR)) {
			System.out.println("loading cows");
			List<Cow> cowsloaded = GardenDataController.loadCows();
			Main.setCows(cowsloaded);
		} else {
			System.out.println("cow.json not found, skip loading");
		}

		if (fileExists(Constants.CHICKEN_DATA_DIR)) {
			System.out.println("loading chickens");
			List<Chicken> chickensloaded = GardenDataController.loadChickens(); // ← receive
			Main.setChickens(chickensloaded);
		} else {
			System.out.println("chicken.json not found, skip loading");
		}

		if (fileExists(Constants.PIG_DATA_DIR)) {
			System.out.println("loading pigs");
			List<Pig> pigsloaded = GardenDataController.loadPigs(); // ← receive
			Main.setPigs(pigsloaded);
		} else {
			System.out.println("pig.json not found, skip loading");
		}

		if (fileExists(Constants.PLANT_DATA_DIR)) {
			System.out.println("loading plants");
			List<Plant> plantsloaded = GardenDataController.loadPlant();
			Main.setPlant(plantsloaded);
		} else {
			System.out.println("plant.json not found, skip loading");
		}

		if (fileExists(Constants.TASK_DATA_DIR)) {
			System.out.println("loading plants");
			ArrayList<Task> tasksloaded = TaskDataController.loadTasks();
			TaskManager.setTasks(tasksloaded);
		} else {
			System.out.println("plant.json not found, skip loading");
		}

		CoinDataController.loadCoin(Constants.COIN_DATA_DIR);

	}

//check json file if exist
	private static boolean fileExists(String path) {

		File file = new File(path);
		return file.exists();
	}

	public static void setCows(List<Cow> cows2) {
		cows = cows2;
	}

	public static void setPigs(List<Pig> pigs2) {
		pigs = pigs2;
	}

	public static void setChickens(List<Chicken> chickens2) {
		chickens = chickens2;
	}

	public static void setPlant(List<Plant> plants2) {
		plants = plants2;
	}

	public static void save() {
		GardenDataController.saveGardenItems();
		TaskDataController.saveTasks();
		CoinDataController.saveCoin(Constants.COIN_DATA_DIR);
	}

//Restore default
	public static void clearData() {
		cows.clear();
		chickens.clear();
		pigs.clear();
		plants.clear();
		addDefaultItems();
		// TaskManager.setCoin(Constants.COIN_DEFAULT);
	}

	public static void buy() {
		UI.println("You have $" + StoreManager.getMoney());
		String input = UI.askString("What to buy: ");
		int positionX = UI.askInt("X Position: ");
		int positionY = UI.askInt("Y position: ");
		switch (input) {
		case "COW":
			Cow newCow = new Cow(positionX, positionY);
			if (StoreManager.canBuy(newCow)) {
				newItems.add(newCow);
				StoreManager.setMoney(StoreManager.getMoney() - newCow.getPrice());
			} else
				UI.println("You can't afford that!");
			break;
		case "CHICKEN":
			Chicken newChicken = new Chicken(positionX, positionY);
			if (StoreManager.canBuy(newChicken)) {
				newItems.add(newChicken);
				StoreManager.setMoney(StoreManager.getMoney() - newChicken.getPrice());
			} else
				UI.println("You can't afford that!");
			break;
		case "PIG":
			Pig newPig = new Pig(positionX, positionY);
			if (StoreManager.canBuy(newPig)) {
				newItems.add(newPig);
				StoreManager.setMoney(StoreManager.getMoney() - newPig.getPrice());
			} else
				UI.println("You can't afford that!");
			break;
		case "TREE":
			Tree newTree = new Tree(positionX, positionY);
			if (StoreManager.canBuy(newTree)) {
				newItems.add(newTree);
				StoreManager.setMoney(StoreManager.getMoney() - newTree.getPrice());
			} else
				UI.println("You can't afford that!");
			break;
		case "FLOWER":
			Flower newFlower = new Flower(positionX, positionY);
			if (StoreManager.canBuy(newFlower)) {
				newItems.add(newFlower);
				StoreManager.setMoney(StoreManager.getMoney() - newFlower.getPrice());
			} else
				UI.println("You can't afford that!");
			break;
		default:
			UI.println("Input not recognized.");
			break;
		}
	}

	/**
	 * drawworld. =======
	 * 
	 * /// updateWorld: /** Moves animals, adds any GardenItems that were bought
	 * from the store to the world, draw world, then waits to repeat. >>>>>>>
	 * refs/heads/main
	 * 
	 * @param int frameRate: How many frames per second the world should be updated.
	 * @return -> N/A.
	 */
	public void updateWorld(int frameRate) throws InterruptedException {

		while (true) {
			for (Cow cow : cows) {
				cow.moveRandomly(); // <- Move animals
			}
			for (Animal chicken : chickens) {
				chicken.moveRandomly(); // <- Move animals
			}
			for (Pig pig : pigs) {
				pig.moveRandomly(); // <- Move animals
			}
			if (newItems.size() > 0) { // <- Add any animals and plants that were bought and stored in newItems to the
										// world.
				for (GardenItem item : newItems) {
					if (item instanceof Cow) {
						cows.add((Cow) item);
					}
					if (item instanceof Pig) {
						pigs.add((Pig) item);
					}
					if (item instanceof Chicken) {
						chickens.add((Chicken) item);
					} else if (item instanceof Plant) {
						plants.add((Plant) item);
					}
				}
				newItems.clear(); // <- Clear newItems now that they're in the right place.
			}
			GUI.drawWorld();
			Thread.sleep(1000 / frameRate); // <- Repeat after 1/frameRate seconds.
		}
	}

	/**
	 * Adds a garden item to the newItems list. UpdateWorld will place it in the
	 * world after the next update.
	 * 
	 * @param GardenItem item: Item to be added to database.
	 * @return -> N/A.
	 */
	public static void addToWorld(GardenItem item) {
		newItems.add(item);
	}

	/**
	 * Returns the garden's height.
	 * 
	 * @param param N/A.
	 * @return -> int.
	 */
	public static int getGardenHeight() {
		return GARDEN_HEIGHT;
	}

	/**
	 * Returns the garden's width.
	 * 
	 * @param param N/A.
	 * @return -> int.
	 */
	public static int getGardenWidth() {
		return GARDEN_WIDTH;
	}

	/**
	 * Returns the the world's plants ArrayList.
	 * 
	 * @param param N/A.
	 * @return -> ArrayList<Animal>.
	 */
	public static List<Plant> getPlants() {
		return plants;
	}

	/**
	 * Returns the the world's animals ArrayList.
	 * 
	 * @param param N/A.
	 * @return -> ArrayList<Animal>.
	 */
//	public static ArrayList<Animal> getAnimals() {
//		return animals;
//	}

	public static List<Cow> getCows() {
		return cows;
	}

	public static List<Chicken> getChickens() {
		return chickens;
	}

	public static List<Pig> getPigs() {
		return pigs;
	}

	/**
	 * Returns a random integer between two values.
	 * 
	 * @param int minValue: the minimum number that can be returned.
	 * @param int maxValue: the maximum number that can be returned.
	 * @return -> int.
	 */
	public static int randomInt(int minValue, int maxValue) {
		return minValue + (int) (Math.random() * (maxValue - minValue + 1));
	}

	/**
	 * Returns a random double between two values.
	 * 
	 * @param double minValue: the minimum number that can be returned.
	 * @param double maxValue: the maximum number that can be returned.
	 * @return -> double.
	 */
	public static double randomDouble(double minValue, double maxValue) {
		return minValue + (Math.random() * (maxValue));
	}

	/*
	 * =============================================================================
	 * =======================================
	 */

	public static void main(String[] args) throws InterruptedException {
		UI.initialise();
		new Main();
	}
}
