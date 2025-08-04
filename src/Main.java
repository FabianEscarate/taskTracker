import java.util.ArrayList;

import Context.CLI.CLI;
import Context.CLI.CLIAdapter;
import Context.CLI.Command;

public class Main {

  String APP_NAME = "Task Tracker";
  String VERSION = "1.0.0";

  public static void main(String[] args) throws Exception {

    ArrayList<Command> commands = new ArrayList<Command>() {
      {
        add(new Command("^add\\s+\"(?<taskDescription>[\\w\\d\\s\\.\\-ñ]+)\"$", CLIAdapter::add));
        add(new Command("^update\\s+(?<params>\\d+\\s+\"[\\w\\s+\\d\\.ñ]*\")$", CLIAdapter::update));
        // add(new Command("remove", App::remove));
        add(new Command("^mark-in-progress\\s+?(?<params>\\d+)$", CLIAdapter::markInProgress));
        // add(new Command("mark done", App::markDone));
        add(new Command("^list\\s*?$", CLIAdapter::list));
        // add(new Command("list done", App::listDone));
        // add(new Command("^list\\s*?pending$", CLIAdapter::listPending));
        // add(new Command("list in-progress", App::listInProgress));
      }
    };

    CLI cli = new CLI(commands);

    cli.run();

  }
}