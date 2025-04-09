import model.TaskList;
import view.TaskManagerView;
import controller.TaskController;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            TaskList model = new TaskList();
            TaskManagerView view = new TaskManagerView(model);
            new TaskController(model, view);
        });
    }
}
