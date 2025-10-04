package src.controller.memento;

import java.util.Objects;

public class Memento<T> {
    private T state;
    public Memento() { }
    public void setState(T state) {
        this.state = state;
    }
    public T getState() {
        return state;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Memento memento)) return false;
        return Objects.equals(state, memento.state);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(state);
    }
}