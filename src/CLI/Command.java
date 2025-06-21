package CLI;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Command {

  String name;
  Pattern regex;
  Action action;
  
  Command(String name, Pattern regex, Action action) {
    this.name = name;
    this.regex = regex;
    this.action = action;
  }
  

  public Command(String regex, Action action) {
    this(extractCommandName(regex), Pattern.compile(regex), action);
  }

  private static String extractCommandName(String regex) {
    String COMMAND_NAME_REGEX =  "(?<commandName>[a-z]+)";
    Pattern pattern = Pattern.compile(COMMAND_NAME_REGEX);
    Matcher matcher = pattern.matcher(regex);
    if (matcher.find()) {
      return matcher.group("commandName");
    }
    throw new IllegalArgumentException("Invalid regex: command name not found");
  }

}
