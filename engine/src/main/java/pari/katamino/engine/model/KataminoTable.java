package pari.katamino.engine.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pari.katamino.engine.util.PrimitiveArrays;

/**
 * Created by xavi on 2016.09.26..
 */
public class KataminoTable {
    private static final Logger LOGGER = LogManager.getLogger(KataminoTable.class);

    public static final int EMPTY = 0;

    private int rows;

    private int columns;

    private int[][] table;

    public KataminoTable(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.table = new int[rows][columns];

        this.resetTable();
    }

    /**
     * Reset the table values to EMPTY.
     */
    public void resetTable() {
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                this.table[i][j] = EMPTY;
            }
        }
    }

    public int getTableValue(int row, int column) {
        return this.table[row][column];
    }

    /**
     * This is going to be the operator.
     * At first checks that it is possible to add the given item.
     * If possible then place an item to first possible place.
     * Free place is checked starting from top left corner then walking right and bottom.
     * @param item the item to add
     * @return true if item is added, otherwise false
     */
    public boolean addItem(KataminoItem item) {
        if(item == null) {
            throw new IllegalArgumentException("item");
        }

        Position position = this.canAddItem(item);
        if(position == null) {
            return false;
        }

        LOGGER.debug("Trying to add the following item to the table: " + item);

        for (int i = 0; i < item.getRows(); i++) {
            for (int j = 0; j < item.getColumns(); j++) {
                if(item.getMapValue(i, j)) {
                    this.table[position.getRow() + i][position.getColumn() + j] = item.getId();
                }
            }
        }

        return true;
    }

    /**
     * Checks the table whether it is possible to add the given item.
     * @return null if it is not possible, the starting position if possible.
     */
    public Position canAddItem(KataminoItem item) {
        if(item == null) {
            throw new IllegalArgumentException("item");
        }

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if(this.table[i][j] == EMPTY && j - item.getFirstColumnIndex() >= 0 && this.canMatchItem(item, i, j - item.getFirstColumnIndex())) {
                    return new Position(i, j - item.getFirstColumnIndex());
                }
            }
        }

        return null;
    }

    /**
     * Removes the given item from the table;
     */
    public void removeItem(KataminoItem item) {
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                if(this.table[i][j] == item.getId()) {
                    this.table[i][j] = EMPTY;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String lineSeparator = System.lineSeparator();

        sb.append("Table");
        sb.append(lineSeparator);
        for (int i = 0; i < this.table.length; i++) {
            sb.append(PrimitiveArrays.join(this.table[i], ", "));
            sb.append(lineSeparator);
        }

        return sb.toString();
    }

    private boolean canMatchItem(KataminoItem item, int row, int column) {
        for (int i = 0; i < item.getRows(); i++) {
            if(row + i >= this.table.length) {
                return false;
            }
            for (int j = 0; j < item.getColumns(); j++) {
                if(column + j >= this.table[i].length) {
                    return false;
                }
                if(item.getMapValue(i, j) && this.table[row + i][column + j] != EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }
}
