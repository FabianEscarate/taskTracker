package App;
import java.util.ArrayList;
import Entities.Task;

public class TaskTracker {
  static ArrayList<Task> DATA = new ArrayList<Task>();

  public static void add(String taskDescription) {
    System.out.println("add method called");
    
    if (taskDescription.isEmpty()) {
      System.out.println("No task description provided.");
      return;
    }

    DATA.add(new Task(taskDescription));

    System.out.println("Task added: " + taskDescription);
  }

  public static void update(int taskId, String newDescription) {
    System.out.println("update method called");

    if(newDescription.isEmpty()){
      System.out.println("No new task description provided.");
      return;
    }

    DATA.get(taskId).setDescription(newDescription);

    System.out.println(String.format("Task %d was updated with new description: %s", taskId, newDescription));
  }

  // public static void remove() {
  //   System.out.println("remove method called");
  // }

  // public static void markInProgress() {
  //   System.out.println("markInProgress method called");
  // }

  // public static void markDone() {
  //   System.out.println("markDone method called");
  // }

  public static void list() {
    System.out.println("list method called");
    System.out.println(String.format("| %-7s|%25s|", "Number", "Description"));
    for (Task task : DATA) {
      System.out.println(String.format("| %-7d|%25s|", task.id, task.description));
    }
    System.out.println();
  }

  // public static void listDone() {
  //   System.out.println("listDone method called");
  // }

  // public static void listTodo() {
  //   System.out.println("listTodo method called");
  // }

  // public static void listInProgress() {
  //   System.out.println("listInProgress method called");
  // }
}
