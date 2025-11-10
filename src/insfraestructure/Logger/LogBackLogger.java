package insfraestructure.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackLogger {
  private final Logger logger;

  public LogBackLogger(Class<?> clazz) {
    this.logger = LoggerFactory.getLogger(clazz);
  }

  public void info(String message) {
    logger.info(message);
  }
  public void error(String message) {
    logger.error(message);
  }
  public void warn(String message) {
    logger.warn(message);
  }
}
