package datacontroller;

import util.FileUtil;
import model.Task;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import ecs100.UI;
import manager.TaskManager;
import util.Constants;

//=======
import model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of Task objects.
 * acts as the bridge between the application's TaskManager
 * and the storage.
 */
 

public class TaskDataController {

	private static List<Task> StoredTasks;

	public static void saveTasks() {
		StoredTasks = TaskManager.getTasks();

		FileUtil.saveObject(StoredTasks, Constants.TASK_DATA_DIR);

	}

	public static ArrayList<Task> loadTasks() {
		Type listType = new TypeToken<List<Task>>() {
		}.getType();

		ArrayList<Task> tasks = FileUtil.loadObject(Constants.TASK_DATA_DIR, listType);
		return tasks;

	}

}
