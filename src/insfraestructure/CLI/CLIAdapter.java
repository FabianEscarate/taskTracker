package insfraestructure.CLI;

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
    Business.TaskTracker.add(taskDescription);
  }

  public static void update(Optional<String[]> args) {
    if (validateArguments(args)) {
      System.out.println("No task description provided.");
      return;
    }
    String[] arguments = args.get();
    int taskId = Integer.parseInt(arguments[0]);
    String newDescription = joinDescripiton(Arrays.copyOfRange(arguments, 1, arguments.length));
    Business.TaskTracker.update(taskId, newDescription);
  }

  public static void markInProgress(Optional<String[]> args) {
    if (validateArguments(args)) {
      System.out.println("No task description provided.");
      return;
    }
    String[] arguments = args.get();
    int taskId = Integer.parseInt(arguments[0]);
    Business.TaskTracker.markInProgress(taskId);
  }

  public static void list(Optional<String[]> args) {
    if (validateArguments(args)) {
      Business.TaskTracker.list(null);
      return;
    }

    Business.TaskTracker.list(args.get()[0]);
  }


}
