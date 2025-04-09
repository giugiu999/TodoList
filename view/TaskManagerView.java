package view;
/*
Display UI interface (including task input box, button, task list)
Implement TaskListObserver interface (automatically refresh when task list changes)
Expose component methods to Controller (e.g. getAddButton())
 */
import model.Task;
import model.TaskList;
import observer.TaskListObserver;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class TaskManagerView extends JFrame implements TaskListObserver {

    private JTextField inputField;        // Input field for new task
    private JButton addButton;            // Button to add a task
    private JButton deleteButton;         // Button to delete a selected task
    private JButton toggleButton;         // Button to toggle task status
    private JList<String> taskJList;      // Display list of tasks
    private DefaultListModel<String> listModel; // List model for task display
    private TaskList model;               // Task data model
    private JComboBox<Task.Level> levelComboBox;

    public TaskManagerView(TaskList model) {
        this.model = model;
        this.model.addObserver(this); // Register as observer

        // ==== Window Settings ====
        setTitle("Task Manager");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ==== Top Panel (input + add button) ====
        JPanel topPanel = new JPanel();
        inputField = new JTextField(20);
        addButton = new JButton("Add Task");
        topPanel.add(inputField);
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);
        //choose box
        levelComboBox = new JComboBox<>(Task.Level.values());
        topPanel.add(new JLabel("Priority:"));
        topPanel.add(levelComboBox);

        // ==== Center Panel (task list) ====
        listModel = new DefaultListModel<>();
        taskJList = new JList<>(listModel);
        add(new JScrollPane(taskJList), BorderLayout.CENTER);

        // ==== Bottom Panel (toggle + delete buttons) ====
        JPanel bottomPanel = new JPanel();
        toggleButton = new JButton("Toggle Status");
        deleteButton = new JButton("Delete Task");
        bottomPanel.add(toggleButton);
        bottomPanel.add(deleteButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true); // Show the window
    }

    // ✅ Refresh task list when model changes
    @Override
    public void onTaskListChanged() {
        listModel.clear();
        List<Task<String>> tasks = model.getTasks();
        for (Task<String> task : tasks) {
            listModel.addElement(task.toString());
        }
    }

    // 👇 Public getters for Controller access

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getToggleButton() {
        return toggleButton;
    }

    public String getInputText() {
        return inputField.getText();
    }

    public int getSelectedIndex() {
        return taskJList.getSelectedIndex();
    }

    public void clearInput() {
        inputField.setText("");
    }
    public Task.Level getSelectedLevel() {
        return (Task.Level) levelComboBox.getSelectedItem();
    }
}

