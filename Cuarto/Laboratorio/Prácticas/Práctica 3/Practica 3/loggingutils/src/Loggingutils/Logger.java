package Loggingutils;

public class Logger {
    private static final java.util.logging.Logger mensajes = java.util.logging.Logger.getLogger(Logger.class.getName());

    private Logger() {
    }


    public static void logInfo(String message) {
        mensajes.info("INFO: " + message);
    }

    public static void logError(String message) {
        mensajes.severe("ERROR: " + message);
    }

    public static void logWarning(String message) {
        mensajes.warning("WARNING: " + message);
    }


}
