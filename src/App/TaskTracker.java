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

  public static void list(String status) {
    ArrayList<ITask> taskByStatus = new ArrayList<>();
    Status statusRequired = status == null ? null : Status.valueOf(status.toUpperCase());
    if (statusRequired == null)
      taskByStatus.addAll(taskRepository.getAllTask());
    if (statusRequired == Status.IN_PROGRESS)
      taskByStatus.addAll(taskRepository.getInProgressTask());
    if (statusRequired == Status.PENDING)
      taskByStatus.addAll(taskRepository.getPendingTask());
    if (statusRequired == Status.COMPLETED)
      taskByStatus.addAll(taskRepository.getCompleteTask());

    System.out.println(String.format("|%-7s|%12s|%25s|", "Number", "Status", "Description"));
    for (ITask itask : taskByStatus) {
      System.out.println(String.format("|%-7d|%12s|%25s|", itask.getId(), itask.getStatus(), itask.getDescription()));
    }
    System.out.println();
  }
}
