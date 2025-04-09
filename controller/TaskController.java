package controller;
import model.Task;
import model.TaskList;
import view.TaskManagerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TaskController {
    private TaskList model;
    private TaskManagerView view;
    public TaskController (TaskList model, TaskManagerView view){
        this.model=model;
        this.view=view;
        //add button event
        view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = view.getInputText().trim();
                if (!content.isEmpty()) {
                    Task.Level level = view.getSelectedLevel();
                    Task<String> newTask = new Task<>(content, level);
                    model.addTask(newTask);
                    view.clearInput();
                } else {
                    JOptionPane.showMessageDialog(view, "Task content cannot be empty.");
                }
            }
        });
        //toggle button event
        view.getToggleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = view.getSelectedIndex();
                if (index >= 0) {
                    model.switchStatus(index);
                } else {
                    JOptionPane.showMessageDialog(view, "Please select a task to toggle.");
                }
            }
        });
        //delete button
        view.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = view.getSelectedIndex();
                if (index >= 0) {
                    model.deleteTask(index);
                } else {
                    JOptionPane.showMessageDialog(view, "Please select a task to delete.");
                }

            }
        });
    }
}
