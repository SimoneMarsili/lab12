package it.unibo.es3;

/**
 * Modelizes the logic for the expansiion grid application.
 */
public interface Logics {

    /**
     * If the grid is fully expanded, the application should quit.
     * 
     * @return the check result
     */
    boolean toQuit();

    /**
     * Checks if a cell is currently active.
     * 
     * @param pos the position of the cell
     * @return the check result
     */
    boolean isActive(Pair<Integer, Integer> pos);

    /**
     * Expands the grid.
     */
    void next();
}
