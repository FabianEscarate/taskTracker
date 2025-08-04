package App;

import java.util.ArrayList;
import App.Domain.Status;
import App.Domain.Task;
import App.Ports.IPortTaskTracker;
import App.Ports.ITask;
import Context.Repository.ProxyTaskRepository;

public class TaskTracker {

  private static IPortTaskTracker taskRepository = (IPortTaskTracker) new ProxyTaskRepository();

  public static void add(String taskDescription) {
    ArrayList<ITask> allTasks = taskRepository.getAllTask();
    if (taskDescription.isEmpty()) {
      System.out.println("No task description provided.");
      return;
    }

    Task newTask = new Task((allTasks.size() + 1), taskDescription);
    taskRepository.add(newTask.toJson());

    System.out.println("Task added: " + taskDescription);
  }

  public static void update(int taskId, String newDescription) {

    if (newDescription.isEmpty()) {
      System.out.println("No new task description provided.");
      return;
    }

    ITask itask = taskRepository.getOneById(taskId);
    if (itask == null)
      return;

    Task task = new Task(itask.getId(), itask.getDescription(), Status.valueOf(itask.getStatus()), itask.getCreateAt(),
        itask.getUpdateAt());

    task.setDescription(newDescription);

    ITask updatedTask = taskRepository.update(taskId, task.toJson());
    if (updatedTask == null) {
      System.out.println("Can't update task");
      return;
    }

    System.out.println(String.format("Task %d was updated with new description: %s", taskId, newDescription));
  }

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

  public static void markInProgress(int taskId) {
    ITask itask = taskRepository.getOneById(taskId);
    if (itask == null)
      return;

    Task task = new Task(itask.getId(), itask.getDescription(), Status.valueOf(itask.getStatus()), itask.getCreateAt(),
        itask.getUpdateAt());
    task.setStatus(Status.IN_PROGRESS.toString());

    ITask updatedTask = taskRepository.update(taskId, task.toJson());
    if (updatedTask == null)
      return;

    System.out.println(String.format("Task %d marked to in progress", updatedTask.getId()));
  }

  // public static void markDone() {
  // System.out.println("markDone method called");
  // }

  public static void list() {
    System.out.println("list method called");
    System.out.println(String.format("|%-7s|%12s|%25s|", "Number", "Status", "Description"));
    for (ITask itask : taskRepository.getAllTask()) {
      System.out.println(String.format("|%-7d|%12s|%25s|", itask.getId(), itask.getStatus(), itask.getDescription()));
    }
    System.out.println();
  }

  // public static void listDone() {
  // System.out.println("listDone method called");
  // }

  // public static void listPending() {
  // System.out.println("listPending method called");
  // System.out.println(String.format("|%-7s|%10s|%25s|", "Number", "Status",
  // "Description"));
  // for (ITask itask : taskRepository.getPedingTask()) {
  // System.out.println(String.format("|%-7d|%10s|%25s|", itask.getId(),
  // itask.getStatus(), itask.getDescription()));
  // }
  // System.out.println();
  // }

  // public static void listInProgress() {
  // System.out.println("listInProgress method called");
  // }
}
