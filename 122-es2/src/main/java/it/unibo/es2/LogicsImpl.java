package it.unibo.es2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final List<List<Boolean>> grid;

    /**
     * Initializes the logic of the grid.
     * 
     * @param size the number of rows and cols of the grid
     */
    public LogicsImpl(final int size) {
        this.grid = IntStream.range(0, size)
            .mapToObj(e -> IntStream.range(0, size)
                .mapToObj(b -> false)
                .collect(Collectors.toList())
            )
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit(final Pair<Integer, Integer> pos) {
        this.grid.get(pos.x()).set(pos.y(), !grid.get(pos.x()).get(pos.y()));
        return grid.get(pos.x()).get(pos.y());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return checkRows() || checkCols();
    }

    /**
     * Checks if at least one row is fully filled with true values.
     * 
     * @return the result of the check
     */
    private boolean checkRows() {
        return this.grid.stream()
            .anyMatch(r -> r.stream().allMatch(b -> b));
    }

    /**
     * Checks if at least one row is fully filled with true values.
     * 
     * @return the result of the check
     */
    private boolean checkCols() {
        return IntStream.range(0, grid.size())
            .anyMatch(c -> grid.stream()
                .allMatch(r -> r.get(c))
            );
    }

}
