package src.util;

public interface Logger {
    void debug(String message);
    void error(String message, Throwable cause);
    void info(String message);
    void info(String message, Object object);
}
