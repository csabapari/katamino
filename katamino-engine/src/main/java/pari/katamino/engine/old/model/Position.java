package pari.katamino.engine.old.model;

/**
 * Created by xavi on 2016.11.04..
 */
public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
}
