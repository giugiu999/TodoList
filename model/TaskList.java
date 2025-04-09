package model;
// Task list,
// store all tasks;
// Provides methods for adding, deleting, and modifying tasks;
// add Observer interface
import observer.TaskListObserver;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task<String>> tasks = new ArrayList<>();
    private List<TaskListObserver> observers = new ArrayList<>();

    private void notifyObservers() {
        for (TaskListObserver obs : observers) {
            obs.onTaskListChanged();
        }
    }
    public void addTask(Task<String> task){
        tasks.add(task);
        notifyObservers();
    }
    public void deleteTask(int index){
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            notifyObservers();
        }
    }
    public void switchStatus(int index){
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).changeStatus();
            notifyObservers();
        }
    }
    public List<Task<String>> getTasks() {
        return tasks;
    }

    public void addObserver(TaskListObserver observer) {
        observers.add(observer);
    }
}
