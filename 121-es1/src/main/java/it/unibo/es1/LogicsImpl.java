package it.unibo.es1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final List<Integer> values;

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        this.values = IntStream.range(0, size)
            .mapToObj(i -> 0)
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.values.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> values() {
        return new ArrayList<>(this.values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {
        return IntStream.range(0, values.size())
            .mapToObj(this::isEnabled)
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        int incremented = this.values.get(elem);
        if (isEnabled(elem)) {
            incremented++;
            this.values.set(elem, incremented);
        }
        return incremented;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
        return this.values.stream()
            .map(String::valueOf)
            .collect(Collectors.joining("|", "<", ">"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return this.values.stream().distinct().count() == 1;
    }

    private boolean isEnabled(final int elem) {
        return this.values.get(elem) < this.values.size();
    }
}
