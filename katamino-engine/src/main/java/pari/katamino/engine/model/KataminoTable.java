package pari.katamino.engine.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pari.katamino.engine.util.PrimitiveArrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a katamino table. It is modelled with a int matrix. Initially all values are EMPTY=0.
 * When katamino item is added the certain matrix position contains the id if the item.
 *
 * Created by xavi on 2016.09.26..
 */
public class KataminoTable {
    private static final Logger LOGGER = LogManager.getLogger(KataminoTable.class);

    public static final int EMPTY = 0;

    private int rows;

    private int columns;

    private int[][] table;

    private List<Integer> addedIds;

    /**
     * Creates a katamino table with given number of rows and columns with EMPTY values.
     */
    public KataminoTable(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.table = new int[rows][columns];
        this.addedIds = new ArrayList<>();

        this.resetTable();
    }

    /**
     * Creates a new KataminoTable based on given one. This is copy constructor.
     */
    public KataminoTable(KataminoTable kataminoTable) {
        if(kataminoTable == null) {
            throw new IllegalArgumentException("kataminoTable cannot be null");
        }

        this.rows = kataminoTable.rows;
        this.columns = kataminoTable.columns;

        this.table = new int[rows][columns];
        for (int i = 0; i < kataminoTable.rows; i++) {
            for (int j = 0; j < kataminoTable.columns; j++) {
                this.table[i][j] = kataminoTable.table[i][j];
            }
        }

        this.addedIds = new ArrayList<>(kataminoTable.addedIds);
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

        this.addedIds.clear();
    }

    /**
     * This is going to be the operator: add an item in certain rotation.
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
        LOGGER.debug("Before:" + this);

        for (int i = 0; i < item.getRows(); i++) {
            for (int j = 0; j < item.getColumns(); j++) {
                if(item.getMapValue(i, j)) {
                    this.table[position.getRow() + i][position.getColumn() + j] = item.getId();
                }
            }
        }

        this.addedIds.add(item.getId());

        LOGGER.debug("After: " + this);

        return true;
    }

    /**
     * Checks the table whether it is possible to add the given item.
     * @return null if it is not possible, the starting position if possible.
     */
    public Position canAddItem(KataminoItem item) {
        if(item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        if(this.addedIds.contains(item.getId())) {
            return null;
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
     * Checks whether the table is full with items.
     * @return If it has at least one empty cell then it return false, otherwise true.
     */
    public boolean isComplete() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if(this.table[i][j] == EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Removes the given item from the table;
     */
    public void removeItem(KataminoItem item) {
        if(item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                if(this.table[i][j] == item.getId()) {
                    this.table[i][j] = EMPTY;
                }
            }
        }
    }

    public int[][] getTable() {
        return this.table;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String lineSeparator = System.lineSeparator();

        sb.append(lineSeparator);
        sb.append("Table");
        sb.append(lineSeparator);
        for (int i = 0; i < this.table.length; i++) {
            sb.append(PrimitiveArrays.join(this.table[i], ", "));
            sb.append(lineSeparator);
        }

        return sb.toString();
    }

    /**
     * This is used for testing only!
     */
    int getTableValue(int row, int column) {
        return this.table[row][column];
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
