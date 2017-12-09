package pari.katamino.gui;

import pari.katamino.engine.model.KataminoItem;
import pari.katamino.engine.model.KataminoTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class KataminoSwingWindow implements ActionListener, SelectionListener, SolutionListener {

    private JFrame mainWindow;

    private List<KataminoItemSelector> items;

    private SolutionPanel solution;

    private JButton solve;

    private JProgressBar progressBar;

    public KataminoSwingWindow()
    {
        this.prepare();
    }

    private void prepare() {
        this.mainWindow = new JFrame("Katamino Solver");
        this.mainWindow.setSize(1000, 800);
        this.mainWindow.setLayout(new BorderLayout());
        this.mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                mainWindow.dispose();
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        this.mainWindow.add(panel, BorderLayout.CENTER);

        this.items = new ArrayList<>();
        this.items.add(new KataminoItemSelector(new Color(255,128,128), new KataminoItem(1, new boolean[][] {{true, true, true, true, true}})).setSelectionListener(this));
        this.items.add(new KataminoItemSelector(new Color(255,0,0), new KataminoItem(2, new boolean[][] {{true, true, true, true}, {true, false, false, false}})).setSelectionListener(this));
        this.items.add(new KataminoItemSelector(new Color(255,128,0), new KataminoItem(3, new boolean[][] {{true, true, true, true}, {false, true, false, false}})).setSelectionListener(this));
        this.items.add(new KataminoItemSelector(new Color(128,64,0), new KataminoItem(4, new boolean[][] {{true, true, true, false}, {false, false, true, true}})).setSelectionListener(this));
        this.items.add(new KataminoItemSelector(new Color(128,128,0), new KataminoItem(5, new boolean[][] {{true, true, true}, {true, false, false}, {true, false, false}})).setSelectionListener(this));
        this.items.add(new KataminoItemSelector(new Color(128,255,0), new KataminoItem(6, new boolean[][] {{true, true, true}, {true, true, false}})).setSelectionListener(this));
        this.items.add(new KataminoItemSelector(new Color(0,128,0), new KataminoItem(7, new boolean[][] {{true, true, true}, {true, false, true}})).setSelectionListener(this));
        this.items.add(new KataminoItemSelector(new Color(0,64,128), new KataminoItem(8, new boolean[][] {{true, false, false}, {true, true, true}, {false, false, true}})).setSelectionListener(this));
        this.items.add(new KataminoItemSelector(new Color(0,0,255), new KataminoItem(9, new boolean[][] {{false, true, false}, {true, true, true}, {false, false, true}})).setSelectionListener(this));
        this.items.add(new KataminoItemSelector(new Color(128,0,64), new KataminoItem(10, new boolean[][] {{true, true, true}, {false, true, false}, {false, true, false}})).setSelectionListener(this));
        this.items.add(new KataminoItemSelector(new Color(128,0,128), new KataminoItem(11, new boolean[][] {{false, false, true}, {false, true, true}, {true, true, false}})).setSelectionListener(this));
        this.items.add(new KataminoItemSelector(new Color(255,0,255), new KataminoItem(12, new boolean[][] {{false, true, false}, {true, true, true}, {false, true, false}})).setSelectionListener(this));

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(4, 3));
        for (KataminoItemSelector item : this.items) {
            itemsPanel.add(item);
        }
        panel.add(itemsPanel);

        this.solution = new SolutionPanel();
        this.solution.setSelectionListener(this);
        panel.add(this.solution);

        this.solve = new JButton("Solve");
        this.solve.setEnabled(false);
        this.solve.addActionListener(this);
        this.mainWindow.add(this.solve, BorderLayout.SOUTH);

        this.progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
        this.mainWindow.add(this.progressBar, BorderLayout.NORTH);
    }

    private void show() {
        this.mainWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.solution.setEnabled(false);
        for (KataminoItemSelector item : this.items) {
            item.setEnabled(false);
        }
        this.solve.setEnabled(false);

        List<KataminoItem> selectedItems = this.collectSelectedItems();

        KataminoSolver solver = new KataminoSolver(this.solution.getNumberOfRows(), selectedItems, this);

        solver.execute();
    }

    public static void main(String[] args)
    {
        new KataminoSwingWindow().show();
    }

    @Override
    public void changed() {
        int rows = this.solution.getNumberOfRows();
        List<KataminoItem> selectedItems = this.collectSelectedItems();
        if(rows >= 3 && rows <= 12 && selectedItems.size() == rows) {
            this.solve.setEnabled(true);
        }
        else {
            this.solve.setEnabled(false);
        }
    }

    private List<KataminoItem> collectSelectedItems() {
        List<KataminoItem> result = new ArrayList<>();
        for (KataminoItemSelector itemSelector : this.items) {
            if(itemSelector.isSelected()) {
                result.add(itemSelector.getKataminoItem());
            }
        }
        return result;
    }

    @Override
    public void started() {
        this.progressBar.setIndeterminate(true);
    }

    @Override
    public void finished(KataminoTable solution) {
        if(solution != null) {
            int[][] table = solution.getTable();
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[i].length; j++) {
                    this.solution.updatePiece(this.findItemColor(table[i][j]), i, j);
                }
            }
        }

        this.progressBar.setIndeterminate(false);
    }

    private Color findItemColor(int itemId) {
        for (KataminoItemSelector selector : this.items) {
            if(selector.getKataminoItem().getId() == itemId) {
                return selector.getItemColor();
            }
        }
        return Color.BLACK;
    }
}
