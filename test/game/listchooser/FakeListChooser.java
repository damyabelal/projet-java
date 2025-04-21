package game.listchooser;

import java.util.List;

public class FakeListChooser<T> implements ListChooser<T> {
    private final T value;

    public FakeListChooser(T value) {
        this.value = value;
    }

    @Override
    public T choose(String message, List<? extends T> list) {
        return value;
    }
}