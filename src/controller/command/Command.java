package src.controller.command;

public interface Command {
    default boolean doesUpdate() {
        return false;
    }

    Object execute(Object... args);
}
