package view;

import model.Task;
import model.TaskList;

import javax.swing.*;
import java.awt.*;

public class TaskCellRenderer extends DefaultListCellRenderer {
    private TaskList model;

    public TaskCellRenderer(TaskList model) {
        this.model = model;
    }
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
                                                  int index, boolean isSelected,
                                                  boolean cellHasFocus){
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus
        );

        Task<String> task = model.getTasks().get(index);

        switch (task.getLevel()) {
            case HIGH:
                label.setForeground(Color.RED);
                break;
            case MEDIUM:
                label.setForeground(new Color(255, 165, 0));
                break;
            case LOW:
                label.setForeground(Color.GRAY);
                break;
        }

        if (task.getstatus()) {
            label.setFont(label.getFont().deriveFont(Font.ITALIC));
        }

        return label;
    }
}
