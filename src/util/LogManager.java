package src.util;

public class LogManager {

    public static Logger getLogger(){
        return new Log4jAdapter();
    }
}