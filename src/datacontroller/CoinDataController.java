package datacontroller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import manager.TaskManager;
import model.StoreItem;
import util.Constants;

public class CoinDataController {
	static int coins = 0;

	/**
	 * save coins to txt file
	 */
	public static void saveCoin(String filePath) {
		// get coins
		int coins = TaskManager.getCoins();

		// make sure director exist
		File file = new File(filePath);
		File parentDir = file.getParentFile();

		if (parentDir != null && !parentDir.exists()) {
			if (!parentDir.mkdirs()) {
				System.err.println("cannot create " + parentDir);
				return;
			}
		}

		// write file
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.println(coins);
			System.out.println(coins + " saved: " + filePath);

		} catch (FileNotFoundException e) {
			System.err.println("save fail");
			e.printStackTrace();
		}
	}

	/**
	 * load coins from file
	 */
	public static void loadCoin(String filePath) {
		File file = new File(filePath);

		// if file doesn't exist, set default value
		if (!file.exists()) {
			System.out.println("coin.txt doesn't exist，Use default value: 100");
			TaskManager.setCoin(Constants.COIN_DEFAULT);
			return;
		}

		// file exist, read file
		try (Scanner scanner = new Scanner(file)) { 

			if (scanner.hasNextInt()) {
				int loadedCoins = scanner.nextInt();
				TaskManager.setCoin(loadedCoins);
				System.out.println("loaded coins: " + loadedCoins);
			} else {
				System.out.println("file invalid，coin =100");
				TaskManager.setCoin(Constants.COIN_DEFAULT);
			}

		} catch (FileNotFoundException e) {
			System.err.println("reading coins fail");
			e.printStackTrace();
			TaskManager.setCoin(Constants.COIN_DEFAULT);
		}
	}
}
