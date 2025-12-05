package datacontroller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import manager.TaskManager;
import model.*;
import model.GardenItem;
import model.Plant;
import model.Task;
import util.Constants;
import util.FileUtil;
import main.Main;

/**
 * Handles the loading and saving of all the GardenItem objects. acts as the
 * bridge between the application's Main Class and the storage.
 */
public class GardenDataController {

	public static void saveGardenItems() {

		List<Cow> cows = Main.getCows();

		FileUtil.saveObject(cows, Constants.COW_DATA_DIR);

		List<Chicken> chickens = Main.getChickens();

		FileUtil.saveObject(chickens, Constants.CHICKEN_DATA_DIR);

		List<Pig> pigs = Main.getPigs();

		FileUtil.saveObject(pigs, Constants.PIG_DATA_DIR);

		List<Plant> plants = Main.getPlants();

		FileUtil.saveObject(plants, Constants.PLANT_DATA_DIR);

	}

	public static List<Pig> loadPigs() {

		Type listType1 = new TypeToken<List<Pig>>() {
		}.getType();

		List<Pig> pigs = FileUtil.loadObject(Constants.PIG_DATA_DIR, listType1);
		return pigs;

	}

	public static List<Chicken> loadChickens() {

		Type listType1 = new TypeToken<List<Chicken>>() {
		}.getType();

		List<Chicken> chickens = FileUtil.loadObject(Constants.CHICKEN_DATA_DIR, listType1);
		return chickens;

	}

	public static List<Cow> loadCows() {

		Type listType2 = new TypeToken<List<Cow>>() {
		}.getType();

		List<Cow> cows = FileUtil.loadObject(Constants.COW_DATA_DIR, listType2);
		return cows;

	}

	public static List<Plant> loadPlant() {

		Type listType3 = new TypeToken<List<Plant>>() {
		}.getType();

		List<Plant> plants = FileUtil.loadObject(Constants.PLANT_DATA_DIR, listType3);
		return plants;

	}

}
