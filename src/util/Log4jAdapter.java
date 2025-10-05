package src.util;

import org.apache.logging.log4j.LogManager;

public class Log4jAdapter implements Logger {
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void error(String message, Throwable cause) {
        logger.error(message, cause);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void info(String message, Object object) {
        logger.info(message, object);
    }
}
