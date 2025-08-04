package Context.CLI;

import java.util.Arrays;
import java.util.Optional;

public class CLIAdapter {

  private static boolean validateArguments(Optional<String[]> args) {
    return args.isEmpty() || args.get().length < 1;
  }

  private static String joinDescripiton(String[] descriptionSplitted) {
    String descriptionJoined = String.join(" ", descriptionSplitted).replaceAll("\"", "");
    return descriptionJoined;
  }

  public static void add(Optional<String[]> args) {
    if (validateArguments(args)) {
      System.out.println("No task description provided.");
      return;
    }
    String taskDescription = String.join(" ", args.get());
    App.TaskTracker.add(taskDescription);
  }

  public static void update(Optional<String[]> args) {
    if (validateArguments(args)) {
      System.out.println("No task description provided.");
      return;
    }
    String[] arguments = args.get();
    int taskId = Integer.parseInt(arguments[0]);
    String newDescription = joinDescripiton(Arrays.copyOfRange(arguments, 1, arguments.length));
    App.TaskTracker.update(taskId, newDescription);
  }

  public static void markInProgress(Optional<String[]> args) {
    if (validateArguments(args)) {
      System.out.println("No task description provided.");
      return;
    }
    String[] arguments =  args.get();
    int taskId =  Integer.parseInt(arguments[0]);
    App.TaskTracker.markInProgress(taskId);
  }

  public static void list(Optional<String[]> args) {
    App.TaskTracker.list();
  }

  // listDone
  public static void listPending(Optional<String[]> args) {
    App.TaskTracker.listPending();
  }
  // listInProgress

}
