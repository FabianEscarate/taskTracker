package Context.CLI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;

public class CLI {

  private ArrayList<Command> defaultCommands = new ArrayList<>() {
    {
      add(new Command("^help\s*?$", args -> {
        System.out.println("Available commands:");
        for (Command command : commands) {
          System.out.println("- " + command.name);
        }
        System.out.println("");
      }));
      add(new Command("^exit\s*?$", args -> {
        System.out.println("Closing the application. Goodbye!");
        System.exit(0);
      }));
    }
  };

  private ArrayList<Command> commands = new ArrayList<>();

  public CLI() {
    // Default constructor
  }

  public CLI(ArrayList<Command> commands) {
    this.commands = this.defaultCommands;
    for (Command command : commands) {
      this.commands.add(command);
    }
  }

  public void run() {
    if (this.commands.isEmpty()) {
      throw new IllegalStateException("No commands available to run.");
    }

    System.out.println("Welcome to Task Tracker CLI!");
    System.out.println("Type 'help' to see available commands.");

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    try {
      while (true) {
        System.out.println("\nEnter a command:");
        System.out.print("> ");
        String command = reader.readLine();
        this.runCommand(command.trim());
      }

    } catch (Exception e) {
      System.out.println("Error reading input: " + e.getMessage());
      return;
    }
  }

  public void setCommands(ArrayList<Command> newCommands) {
    this.commands = newCommands;
  }

  private void runCommand(String input) {
    for (Command command : commands) {
      Matcher matcher = command.regex.matcher(input);
      if (matcher.matches()) {
        command.action.run(input.isEmpty() ? Optional.empty()
            : matcher.groupCount() > 0
                ? matcher.group(1) != null ? Optional.of(matcher.group(1).trim().split("\\s+")) : Optional.empty()
                : Optional.empty());
        return;
      }
    }
    throw new IllegalArgumentException("Command not found: " + input);
  }

}
