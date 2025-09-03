public class Logger {
  private static Logger instance;

  private Logger() {
  }

  public static Logger getInstance() {
    if (instance == null) {
      instance = new Logger();
    }
    return instance;
  }

  public void logInfo(String message) {
    System.out.println("INFO: " + message);
  }

  public void logError(String message) {
    System.err.println("ERROR: " + message);
  }

  public void logWarning(String message) {
    System.out.println("WARNING: " + message);
  }

}