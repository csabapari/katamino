package pari.katamino.engine.model;

import pari.katamino.engine.util.PrimitiveArrays;

/**
 * Defines a katamino item with a boolean map (or matrix).
 * For example if the map:
 * <pre>
 * false false true false
 * true  true  true true
 * </pre>
 * then
 * <ul>
 * <li>getRows returns: 2</li>
 * <li>getColumns returns: 4</li>
 * <li>getFirstColumnIndex: 2</li>
 * </ul>
 *
 * Created by xavi on 2016.09.26..
 */
public class KataminoItem {

    private int id;

    /**
     * First index is the ROW and second index is the COLUMN;
     */
    private boolean[][] map;

    private int firstColumnIndex;

    /**
     * Creates a katamino item from an input map. The input map must not have empty rows or columns.
     * Empty means all position in the row is false.
     * @param id
     * @param map
     */
    public KataminoItem(int id, boolean[][] map) {

        this.id = id;

        //Assumption: the input map is optimal (no empty rows or columns)
        //int notEmptyRows = this.countNotEmptyRows(map);
        //int notEmptyColumns = this.countNotEmptyColumns(map);
        //this.map = new boolean[notEmptyRows][notEmptyColumns];
        this.map = map;

        for (int i = 0; i < this.map[0].length; i++) {
            if(this.map[0][i]) {
                this.firstColumnIndex = i;
            }
        }
    }

    /**
     * The id of the katamino item. Each item must have unique id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * The number of rows of the item map.
     */
    public int getRows() {
        return this.map.length;
    }

    /**
     * The number of columns of the item map.
     */
    public int getColumns() {
        if(this.map.length == 0) {
            return 0;
        }
        return this.map[0].length;
    }

    /**
     * The first column which has true value in the first row.
     * This important when the item is added to the katamino table.
     * See {@link KataminoTable#addItem(KataminoItem)} and {@link KataminoTable#canAddItem(KataminoItem)}
     */
    public int getFirstColumnIndex() {
        return this.firstColumnIndex;
    }

    /**
     * Provides the value of the item map in a certain position.
     */
    public boolean getMapValue(int row, int column) {
        if(row < 0 && row >= this.map.length)  {
            throw new IllegalArgumentException("row");
        }
        if(column < 0 && column >= this.map[row].length) {
            throw new IllegalArgumentException("column");
        }

        return this.map[row][column];
    }

    /**
     * Rotate the katamino item and returns a new katamino item.
     * Possible turn degrees: 0, 90, 180, 270. See {@link TurnDegrees}
     */
    public KataminoItem rotate(TurnDegrees variant) {
        boolean[][] result = new boolean[0][0];

        if(this.map.length == 0) {
            return this;
        }

        if(variant == TurnDegrees.Zero) {
            result = new boolean[this.map.length][this.map[0].length];

            for (int i = 0; i < this.map.length; i++) {
                for (int j = 0; j < this.map[i].length; j++) {
                    result[i][j] = this.map[i][j];
                }
            }
        } else if(variant == TurnDegrees.Ninety) {
            result = new boolean[this.map[0].length][this.map.length];

            for (int i = 0; i < this.map[0].length ; i++) {
                for (int j = this.map.length - 1; j >= 0 ; j--) {
                    result[i][this.map.length - 1 - j] = this.map[j][i];
                }
            }
        } else if(variant == TurnDegrees.HundredEighty) {
            result = new boolean[this.map.length][this.map[0].length];

            for (int i = 0; i < this.map.length; i++) {
                for (int j = 0; j < this.map[i].length; j++) {
                    result[this.map.length - 1- i][this.map[i].length - 1- j] = this.map[i][j];
                }
            }

        } else if(variant == TurnDegrees.TwoHundredSeventy) {
            result = new boolean[this.map[0].length][this.map.length];

            for (int i = 0; i < this.map[0].length; i++) {
                for (int j = 0; j < this.map.length; ++j) {
                    result[this.map[0].length - 1 - i][j] = this.map[j][i];
                }
            }
        }

        return new KataminoItem(this.id, result);
    }

    /**
     * Vertically mirrors the item.
     * @return The mirrored item.
     */
    public KataminoItem mirror() {
        if(this.map.length == 0) {
            return this;
        }
        boolean[][] result = new boolean[this.map.length][this.map[0].length];

        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                result[i][this.map[i].length - 1 - j] = this.map[i][j];
            }
        }

        return new KataminoItem(this.id, result);
    }

    /**
     * This is not perfect like this.
     * Two katamino items are identical if they have same id, map and firstColumnIndex.
     * QUESTION: for example an item and its 90 degree rotation is also the same.
     * ANSWER: Right now this used during testing, there it is needed in that way.
     * Therefore be careful when it is used in other parts of the application!
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof KataminoItem)) {
            return false;
        }

        KataminoItem item = (KataminoItem)obj;

        if(this.id != item.id) {
            return false;
        }

        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                if(item.map[i][j] != this.map[i][j]) {
                    return false;
                }
            }
        }

        if(this.firstColumnIndex != item.firstColumnIndex) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String lineSeparator = System.lineSeparator();

        sb.append(lineSeparator);
        sb.append("Id: " + this.id);
        sb.append(lineSeparator);

        for (int i = 0; i < this.map.length ; i++) {
            sb.append(PrimitiveArrays.join(this.map[i], ", "));
            sb.append(lineSeparator);
        }

        return sb.toString();
    }

    /**
     * Counts the not empty rows of the item descriptor map.
     * TODO later: at the moment there is no map validation
     */
    private int countNotEmptyRows(boolean[][] map) {
        int notEmptyRows = 0;
        for (int i = 0; i < map.length; i++) {
            if(!PrimitiveArrays.isEmpty(map[i])) {
                notEmptyRows++;
            }
        }

        return notEmptyRows;
    }

    /**
     * TODO later: at the moment there is no map validation
     */
    private int countNotEmptyColumns(boolean[][] map) {
        int notEmptyColumns = 0;

        if(map.length == 0) {
            return notEmptyColumns;
        }

        for(int i = 0; i < map[0].length; ++i) {
            boolean[] column = new boolean[map.length];
            for (int j = 0; j < map.length; j++) {
                column[j] = map[j][i];
            }
            if(!PrimitiveArrays.isEmpty(column)) {
                notEmptyColumns++;
            }
        }

        return notEmptyColumns;
    }
}
