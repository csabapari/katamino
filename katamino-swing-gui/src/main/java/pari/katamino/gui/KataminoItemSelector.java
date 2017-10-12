package pari.katamino.gui;

import pari.katamino.engine.model.KataminoItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KataminoItemSelector extends JPanel {

    private KataminoItem item;

    private boolean selected;

    private Color itemColor;

    private SelectionListener selectionListener;

    private boolean enabled;

    public KataminoItemSelector(Color itemColor, KataminoItem item) {
        this.enabled = true;
        this.item = item;
        this.selected = false;
        this.itemColor = itemColor;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                itemClicked(e);
            }
        });
    }

    public KataminoItem getKataminoItem() {
        return this.item;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public Color getItemColor() { return this.itemColor; }

    @Override
    protected void paintComponent(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();

        g.setColor(this.selected ? Color.LIGHT_GRAY : Color.GRAY);
        g.fillRect(0,0, width, height);

        int pieceWidth = width / 7;
        int pieceHeight = height / 7;

        g.setColor(this.itemColor);

        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 7; ++j) {
                if(i < 1 || i > 5 || j < 1 || j > 5) {
                    continue;
                }
                if(i - 1 < this.item.getRows() && j - 1 < this.item.getColumns() && this.item.getMapValue(i - 1, j -1)) {
                    g.fillRect(j * pieceWidth, i * pieceHeight, pieceWidth, pieceHeight);
                }
            }
        }
    }

    public KataminoItemSelector setSelectionListener(SelectionListener selectionListener) {
        this.selectionListener = selectionListener;
        return this;
    }

    public void itemClicked(MouseEvent e) {
        if(!this.enabled) {
            return;
        }
        this.selected = !this.selected;
        this.repaint();
        if(this.selectionListener != null) {
            this.selectionListener.changed();
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
