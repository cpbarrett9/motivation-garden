package ui;

import manager.TaskManager;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TodoPanel extends JPanel {

    private DefaultListModel<Task> listModel = new DefaultListModel<>();
    private JList<Task> taskList = new JList<>(listModel);
    private JLabel coinsLabel = new JLabel();

    public TodoPanel() {
        setLayout(new BorderLayout());

        // Coins label setup
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        coinsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(coinsLabel, BorderLayout.NORTH);

        // Task list setup
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add Task");
        JButton deleteBtn = new JButton("Delete Task");
        JButton markBtn = new JButton("Mark Done");
        buttonPanel.add(addBtn);
        buttonPanel.add(markBtn);
        buttonPanel.add(deleteBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addBtn.addActionListener(e -> addTask());
        deleteBtn.addActionListener(e -> deleteTask());
        markBtn.addActionListener(e -> markTaskDone());

        // Listen for task/coin changes
        TaskManager.addListener(this::refreshTaskList);
        refreshTaskList();
    }

    // Refresh the task list and coins label
    private void refreshTaskList() {
        SwingUtilities.invokeLater(() -> {
            listModel.clear();
            ArrayList<Task> tasks = TaskManager.getTasks();
            for (Task t : tasks) {
                listModel.addElement(t);
            }
            coinsLabel.setText("💰 Coins: " + TaskManager.getCoins());
        });
    }

    // Add task dialog
    private void addTask() {
        // Categories
        String[] categories = {
            "Easy Wins",
            "Nutrition",
            "Movement",
            "Connection",
            "Productivity",
            "Your own task"
        };

        // Suggested tasks for each category (skip last)
        String[][] suggestedTasks = {
            {"Get out of bed","Change clothes","Take 3 deep breaths","Wash face","Look in the mirror"}, // Easy Wins
            {"Drink water / tea","Eat healthy meals","Buy healthy snacks","Add vegetables to meal","Try a new recipe"},   // Nutrition
            {"Take a stretch break","Go for a 5-minute walk in nature","Dance to a song I like","Take the stairs","Do 5 squats"},        // Movement
            {"Say thank you to someone","Express gratitude to a loved one","Compliment someone","Give someone a warm hug","Cook a family meal together" }, // Connection
            {"Write down my goals for tomorrow","Break a task into smaller steps","Set a deadline for a task","Check off one item from my to-do list", "Finish work on time"}     // Productivity
        };

        // Category combo box
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        categoryBox.setEditable(true);

        // Task combo box, editable
        JComboBox<String> taskBox = new JComboBox<>();
        taskBox.setEditable(true);

        // Update taskBox based on selected category
        categoryBox.addActionListener(e -> {
            String selectedCategory = (String) categoryBox.getSelectedItem();
            taskBox.removeAllItems();
            boolean found = false;

            for (int i = 0; i < suggestedTasks.length; i++) {
                if (categories[i].equalsIgnoreCase(selectedCategory)) {
                    for (String task : suggestedTasks[i]) {
                        taskBox.addItem(task);
                    }
                    found = true;
                    break;
                }
            }

            // If "Your own task" or a custom category, leave editable
            if (!found) taskBox.addItem("");
            taskBox.setSelectedItem("");
        });

        // Initialize taskBox with first category
        categoryBox.setSelectedIndex(0);

        // Layout panel
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Category:"));
        panel.add(categoryBox);
        panel.add(new JLabel("Task:"));
        panel.add(taskBox);

        // Show dialog
        int result = JOptionPane.showConfirmDialog(this, panel, "Add a new task", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String category = (String) categoryBox.getSelectedItem();
            String title = (String) taskBox.getSelectedItem();

            if (category != null && !category.trim().isEmpty() &&
                title != null && !title.trim().isEmpty()) {
                TaskManager.addTask(title.trim(), category.trim());
            } else {
                JOptionPane.showMessageDialog(this, "Category and task cannot be empty!");
            }
        }
    }

    // Delete selected task
    private void deleteTask() {
        Task selected = taskList.getSelectedValue();
        if (selected != null) {
            TaskManager.deleteTask(selected.getTitle());
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete!");
        }
    }

    // Mark selected task as done
    private void markTaskDone() {
        Task selected = taskList.getSelectedValue();
        if (selected != null) {
            TaskManager.markTaskDone(selected.getTitle());
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to mark done!");
        }
    }
}
