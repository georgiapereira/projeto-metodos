package src.controller.memento;

import org.jetbrains.annotations.NotNull;

public abstract class MementoSource<T> {
    private Memento<T> memento;

    protected abstract Memento<T> createMemento();

    public void save() {
        memento = createMemento();
    }

    public boolean undo() {
        Memento<T> m = createMemento();
        if (memento.equals(m)) {
            return false;
        }

        restore(memento);
        return true;
    }

    protected abstract void restore(@NotNull Memento<T> m);
}
