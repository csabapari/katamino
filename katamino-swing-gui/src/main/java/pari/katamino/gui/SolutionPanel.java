package pari.katamino.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SolutionPanel extends JPanel {

    private static Color EMPTY = Color.DARK_GRAY;

    private static Color UNUSED = Color.BLACK;

    private static Color BORDER = Color.GRAY;

    private JPanel[][] solution;

    private int numberOfRows = 12;

    private SelectionListener selectionListener;

    private boolean enabled;

    public SolutionPanel() {
        this.enabled = true;

        this.setLayout(new GridLayout(12,5));

        this.solution = new JPanel[12][5];
        for (int i = 0; i < this.solution.length; ++i) {
            for (int j = 0; j < this.solution[i].length; ++j) {
                JPanel piece = new JPanel();
                piece.setBackground(EMPTY);
                piece.setBorder(BorderFactory.createLineBorder(BORDER));
                piece.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleMouseClicked(e);
                    }
                });
                this.solution[i][j] = piece;
                this.add(piece);
            }
        }
    }

    public void handleMouseClicked(MouseEvent event) {
        if(!this.enabled) {
            return;
        }

        Component clickedPanel = event.getComponent();

        loop: for (int i = 0; i < this.solution.length; ++i) {
            for (int j = 0; j < this.solution[i].length; ++j) {
                if(clickedPanel == this.solution[i][j]) {
                    if(i == numberOfRows) {
                        numberOfRows = 12;
                    }
                    else if(i >= 3) {
                        this.numberOfRows = i;
                    }

                    break loop;
                }
            }
        }

        for (int i = 0; i < this.solution.length; ++i) {
            for (int j = 0; j < this.solution[i].length; ++j) {
                if(i >= this.numberOfRows) {
                    this.solution[i][j].setBackground(UNUSED);
                }
                else {
                    this.solution[i][j].setBackground(EMPTY);
                }
            }
        }

        if(this.selectionListener != null) {
            this.selectionListener.changed();
        }
    }

    public void updatePiece(Color color, int row, int column) {
        if(row < 0 || row >= this.solution.length || column < 0 || column >= this.solution[row].length) {
            throw new IllegalArgumentException();
        }

        this.solution[row][column].setBackground(color);
        this.solution[row][column].repaint();
    }

    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public void setSelectionListener(SelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
