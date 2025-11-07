import java.util.ArrayList;

import insfraestructure.CLI.CLI;
import insfraestructure.CLI.CLIAdapter;
import insfraestructure.CLI.Command;
import insfraestructure.Web.WebApi;
import insfraestructure.Web.WebPage;
public class Main {

  String APP_NAME = "Task Tracker";
  String VERSION = "1.0.0";

  @SuppressWarnings("unused")
  public static void main(String[] args) throws Exception {

    ArrayList<Command> commands = new ArrayList<Command>() {
      {
        add(new Command("^add\\s+\"(?<taskDescription>[\\w\\d\\s\\.\\-ñ]+)\"$", CLIAdapter::add));
        add(new Command("^update\\s+(?<params>\\d+\\s+\"[\\w\\s+\\d\\.ñ]*\")$", CLIAdapter::update));
        add(new Command("^mark-in-progress\\s+?(?<params>\\d+)$", CLIAdapter::markInProgress));
        add(new Command("^list\\s*(?<params>\\s+pending|\\s+completed|\\s+in_progress)?$", CLIAdapter::list));
      }
    };

    CLI cli = new CLI(commands);

    if(false){
      cli.run();
    }

    WebPage.run();
    WebApi.run();

  }
}