package view;

import model.Task;
import model.TaskList;
import observer.TaskListObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TaskManagerView extends JFrame implements TaskListObserver {

    private JTextField inputField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton toggleButton;
    private JList<String> taskJList;
    private DefaultListModel<String> listModel;
    private TaskList model;
    private JComboBox<Task.Level> levelComboBox;

    public TaskManagerView(TaskList model) {
        this.model = model;
        this.model.addObserver(this);

        // ==== Window Settings ====
        setTitle("📝 Task Manager");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // ==== Top Panel ====
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(new Color(245, 245, 245)); // 浅灰色背景
        inputField = new JTextField(15);
        addButton = new JButton("Add Task");
        levelComboBox = new JComboBox<>(Task.Level.values());

        topPanel.add(new JLabel("Task:"));
        topPanel.add(inputField);
        topPanel.add(new JLabel("Priority:"));
        topPanel.add(levelComboBox);
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        // ==== Center Panel (task list) ====
        listModel = new DefaultListModel<>();
        taskJList = new JList<>(listModel);
        taskJList.setCellRenderer(new TaskCellRenderer(model));
        taskJList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        taskJList.setFixedCellHeight(25);
        add(new JScrollPane(taskJList), BorderLayout.CENTER);

        // ==== Bottom Panel ====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        toggleButton = new JButton("✓ Toggle Status");
        deleteButton = new JButton("🗑 Delete Task");

        bottomPanel.setBackground(new Color(245, 245, 245));
        bottomPanel.add(toggleButton);
        bottomPanel.add(deleteButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // ==== Apply Style ====
        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 13);
        addButton.setFont(buttonFont);
        toggleButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void onTaskListChanged() {
        listModel.clear();
        List<Task<String>> tasks = model.getTasks();
        for (Task<String> task : tasks) {
            listModel.addElement(task.toString());
        }
    }

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
