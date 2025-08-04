package App;

import java.util.ArrayList;
import java.util.List;

import App.Domain.Task;
import App.Ports.IPortTaskTracker;
import App.Ports.ITask;
import Context.Repository.ProxyTaskRepository;

public class TaskTracker {

  private static IPortTaskTracker taskRepository = (IPortTaskTracker) new ProxyTaskRepository();

  public static void add(String taskDescription) {
    System.out.println("add method called");
    ArrayList<ITask> allTasks = taskRepository.getAllTask();
    if (taskDescription.isEmpty()) {
      System.out.println("No task description provided.");
      return;
    }

    Task newTask = new Task(taskDescription);
    newTask.setId(allTasks.size() + 1);
    taskRepository.add(newTask.toJson());

    System.out.println("Task added: " + taskDescription);
  }

  // public static void update(int taskId, String newDescription) {
  // System.out.println("update method called");

  // if(newDescription.isEmpty()){
  // System.out.println("No new task description provided.");
  // return;
  // }

  // taskRepository.update(taskId, newDescription);

  // System.out.println(String.format("Task %d was updated with new description:
  // %s", taskId, newDescription));
  // }

  // public static void remove(int taskId) {
  // System.out.println("remove method called");

  // if( taskId < 1){
  // System.out.println("No Id to remove");
  // return;
  // }

  // Task taskToRemove = taskRepository.read(taskId);
  // taskRepository.remove(taskId);

  // System.out.println(String.format("Task %d was removed", taskToRemove.id));
  // }

  // public static void markInProgress() {
  // System.out.println("markInProgress method called");
  // }

  // public static void markDone() {
  // System.out.println("markDone method called");
  // }

  public static void list() {
    System.out.println("list method called");
    System.out.println(String.format("|%-7s|%10s|%25s|", "Number", "Status", "Description"));
    for (ITask itask : taskRepository.getAllTask()) {
      System.out.println(String.format("|%-7d|%10s|%25s|", itask.getId(), itask.getStatus(), itask.getDescription()));
    }
    System.out.println();

  }

  // public static void listDone() {
  // System.out.println("listDone method called");
  // }

  // public static void listTodo() {
  // System.out.println("listTodo method called");
  // }

  // public static void listInProgress() {
  // System.out.println("listInProgress method called");
  // }
}
