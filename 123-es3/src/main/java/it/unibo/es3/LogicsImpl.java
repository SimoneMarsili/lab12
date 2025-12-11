package it.unibo.es3;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of Logics.
 */
public class LogicsImpl implements Logics {

    private static final int STARTING_CELLS = 3;
    private final List<List<Boolean>> grid;

    /**
     * Initializes the logic of the application.
     * 
     * @param width the number of rows and cols of the grid
     */
    public LogicsImpl(final int width) {
        this.grid = IntStream.range(0, width)
            .mapToObj(r -> IntStream.range(0, width)
                .mapToObj(e -> false)
                .collect(Collectors.toList())
            )
            .collect(Collectors.toList());
        casualHits();
    }

    private void casualHits() {
        final List<Integer> indexs = IntStream.range(0, grid.size() * grid.size())
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(indexs);
        for (int i = 0; i < STARTING_CELLS; i++) {
            hit(indexs.get(i) / grid.size(), indexs.get(i) % grid.size());
        }
    }

    private void hit(final int r, final int c) {
        grid.get(r).set(c, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive(final Pair<Integer, Integer> pos) {
        return grid.get(pos.x()).get(pos.y());
    }

    private boolean isValid(final Pair<Integer, Integer> pos) {
        return pos.x() >= 0 && pos.x() < grid.size()
            && pos.y() >= 0 && pos.y() < grid.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void next() {
        IntStream.range(0, grid.size())
            .boxed()
            .flatMap(r -> IntStream.range(0, grid.size())
                .mapToObj(c -> new Pair<>(r, c))
            )
            .filter(this::hasActiveNeighbor)
            .collect(Collectors.toSet())
            .forEach(p -> hit(p.x(), p.y()));
    }

    private boolean hasActiveNeighbor(final Pair<Integer, Integer> pos) {
        return IntStream.rangeClosed(-1, 1)
        .boxed()
        .flatMap(dr -> IntStream.rangeClosed(-1, 1) 
            .filter(dc -> dc != 0 || dr != 0)
            .mapToObj(dc -> new Pair<>(dr + pos.x(), dc + pos.y()))
        )
        .filter(this::isValid)
        .anyMatch(this::isActive);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return grid.stream()
            .allMatch(r -> r.stream().allMatch(b -> b));
    }
}
