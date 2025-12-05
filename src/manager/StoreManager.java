package manager;

import java.util.List;
import java.util.ArrayList;

import main.Main;
import model.GardenItem;
import model.StoreItem;

public class StoreManager {

    private static List<StoreItem> storeItems;
    private static int money;

    public static void init() {
//        storeItems = StoreDataController.loadStoreItems();
//        if (storeItems == null) {
//            storeItems = new ArrayList<>();
//        }

        // Initialize money from TaskManager
        money = TaskManager.getCoins();

        // Add listener so money stays in sync when TaskManager updates
        TaskManager.addListener(() -> money = TaskManager.getCoins());
    }

    public static boolean canBuy(GardenItem item) {
        return item.getPrice() <= money;
    }

    public static boolean buyItem(GardenItem item) {
        if (canBuy(item)) {
            TaskManager.addCoins(-item.getPrice()); // deduct shared coins
            Main.addToWorld(item);
            return true;
        }
        return false;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int moneyToAdd) {
        money = moneyToAdd;
        TaskManager.addCoins(moneyToAdd - money);
    }
}
