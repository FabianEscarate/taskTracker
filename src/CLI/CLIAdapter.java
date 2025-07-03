package CLI;

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
    System.out.println("Adapter add method called with args: " + taskDescription);
    App.TaskTracker.add(taskDescription);
  }

  public static void update(Optional<String[]> args) {
    if (validateArguments(args)) {
      System.out.println("No task description provided.");
      return;
    }
    String [] arguments = args.get();
    int taskId = Integer.parseInt(arguments[0]);
    String newDescription = joinDescripiton(Arrays.copyOfRange(arguments, 1, arguments.length));
    System.out.println("Adapter update method called wth args: " + String.join(", ", args.get()));
    App.TaskTracker.update(taskId, newDescription);
  }
  // remove
  // markInProgress
  // markDone
  public static void list(Optional<String[]> args){
    // App.TaskTracker.list();
  }
  // listDone
  // listTodo
  // listInProgress

}
