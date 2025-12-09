package it.unibo.es2;

/**
 * Modelizes the Logic for the grid application.
 */
public interface Logics {

    /**
     * The actual values of a cell in the grid should change if the cell is clicked.
     * 
     * @param pos the position of the cell (x,y)
     * @return the new value of the cell
     */
    boolean hit(Pair<Integer, Integer> pos);

    /**
     * Check if there's at least one row or column with all true.
     * 
     * @return whether the gui should quit or not
     */
    boolean toQuit();
}
