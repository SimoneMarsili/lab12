package it.unibo.es3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final List<JButton> cells = new ArrayList<>();
    private final transient Logics logic;

    /**
     * Constructor.
     *
     * @param width the size of the grid
     */
    public GUI(final int width) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.logic = new LogicsImpl(width);
        // Create a panel with a grid layout
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel panel = new JPanel(new GridLayout(width, width));
        this.getContentPane().add(mainPanel);
        mainPanel.add(panel, BorderLayout.CENTER);
        // Create buttons and add them to the panel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final JButton button = new JButton("");
                if (logic.isActive(new Pair<>(i, j))) {
                        button.setText(".");
                    }
                this.cells.add(button);
                panel.add(button);
            }
        }
        final JButton next = new JButton(">>");
        next.addActionListener(e -> {
            logic.next();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (logic.isActive(new Pair<>(i, j))) {
                        cells.get(i * width + j).setText(".");
                    }
                }
            }
            if (logic.toQuit()) {
                dispose();
            }
        });
        mainPanel.add(next, BorderLayout.SOUTH);
        pack();
        this.setVisible(true);
    }
}
