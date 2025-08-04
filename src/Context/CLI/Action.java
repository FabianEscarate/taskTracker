package Context.CLI;

import java.util.Optional;

@FunctionalInterface
public interface Action {
   void run(Optional<String[]> args);
}
