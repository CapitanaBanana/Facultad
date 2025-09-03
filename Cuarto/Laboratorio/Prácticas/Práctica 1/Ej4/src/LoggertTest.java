
public class LoggertTest {
  private Logger logger;

  public static void main(String[] args) {
    LoggertTest test = new LoggertTest();
    test.testLogger();
  }

  public void testLogger() {
    logger = Logger.getInstance();
    logger.logInfo("This is an info message.");
    logger.logWarning("This is a warning message.");
    logger.logError("This is an error message.");
  }
}