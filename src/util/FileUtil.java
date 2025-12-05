package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ecs100.UI;
import manager.TaskManager;
import model.Task;

/**
 * FileUtil - manage file reading, writing
 */

public class FileUtil {

	/**
	 * Create a Gson object (formatted output for readability)
	 */
	private static Gson createGson() {
		return new GsonBuilder().setPrettyPrinting() // formatted output
				.create();
	}

	/**
	 * Save an object to a JSON file
	 * 
	 * @param object   The object to be saved
	 * @param filePath The file path
	 * @return boolean Whether the operation succeeded
	 */
	public static boolean saveObject(Object object, String filePath) {
		try {
			// make sure directory exist
			File file = new File(filePath);
			File parentDir = file.getParentFile();
			if (parentDir != null && !parentDir.exists()) {
				parentDir.mkdirs(); // Create directory

			}

			// Convert to JSON string
			Gson gson = createGson();
			String json = gson.toJson(object);

			// write into file
			FileWriter writer = new FileWriter(filePath);
			writer.write(json);
			writer.close();

			System.out.println(">>> Saved to: " + filePath);
			return true;

		} catch (IOException e) {
			System.err.println(">>> Error saving file: " + e.getMessage());
			return false;
		}
	}

	public static <T> T loadObject(String filePath, Type type) {
		try {

			File file = new File(filePath);

			// check file is Exsited
			if (!file.exists()) {
				System.out.println(">>> File not found: " + filePath);
				return null;
			}

			FileReader reader = new FileReader(file); // read file
			BufferedReader bufferedReader = new BufferedReader(reader); // make reading faster

			StringBuilder json = new StringBuilder();
			String line;

			while ((line = bufferedReader.readLine()) != null) {

				json.append(line);

			}

			bufferedReader.close();
			Gson gson = new Gson();

			T object = gson.fromJson(json.toString(), type);

			System.out.println(">>> Loaded from: " + filePath);
			return object;

		} catch (IOException e) {
			System.err.println(">>> Error loading file: " + e.getMessage());
			return null;

		}

	}



}
