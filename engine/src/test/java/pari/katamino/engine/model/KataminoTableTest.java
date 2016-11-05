package pari.katamino.engine.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xavi on 2016.11.02..
 */
public class KataminoTableTest {

    @Test
    public void canResetTable() {
        final int rows = 3;
        final int columns = 5;

        KataminoTable table = new KataminoTable(rows, columns);

        this.assertEmptyTable(table, rows, columns);
    }

    @Test
    public void canAddOneItem() {
        final int[][] expectedTable = new int[][] {
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        final int rows = 3;
        final int columns = 5;

        KataminoTable table = new KataminoTable(rows, columns);

        KataminoItem item = new KataminoItem(1, new boolean[][] {{true, true, true, true}, {true, false, false, false}});

        table.addItem(item);

        this.assertTable(expectedTable, table);
    }

    @Test
    public void canAddThreeItems() {
        final int[][] expectedTable = new int[][] {
                {1, 1, 1, 1, 3},
                {1, 2, 2, 2, 3},
                {2, 2, 3, 3, 3}
        };

        final int rows = 3;
        final int columns = 5;

        KataminoTable table = new KataminoTable(rows, columns);

        KataminoItem item = new KataminoItem(1, new boolean[][] {{true, true, true, true}, {true, false, false, false}});
        table.addItem(item);

        item = new KataminoItem(2, new boolean[][]{{false, true, true, true}, {true, true, false, false}});
        table.addItem(item);

        item = new KataminoItem(3, new boolean[][]{{false, false, true}, {false, false, true}, {true, true, true}});
        table.addItem(item);

        this.assertTable(expectedTable, table);
    }

    @Test
    public void canRemoveItem() {
        final int[][] expectedTable = new int[][] {
                {1, 1, 1, 1, 3},
                {1, 0, 0, 0, 3},
                {0, 0, 3, 3, 3}
        };

        final int rows = 3;
        final int columns = 5;

        KataminoTable table = new KataminoTable(rows, columns);

        KataminoItem item1 = new KataminoItem(1, new boolean[][] {{true, true, true, true}, {true, false, false, false}});
        table.addItem(item1);

        KataminoItem item2 = new KataminoItem(2, new boolean[][]{{false, true, true, true}, {true, true, false, false}});
        table.addItem(item2);

        KataminoItem item3 = new KataminoItem(3, new boolean[][]{{false, false, true}, {false, false, true}, {true, true, true}});
        table.addItem(item3);

        table.removeItem(item2);

        this.assertTable(expectedTable, table);
    }

    private void assertTable(int[][] expectedTable, KataminoTable table) {
        for (int i = 0; i < expectedTable.length; i++) {
            for (int j = 0; j < expectedTable[i].length; j++) {
                Assert.assertEquals(expectedTable[i][j], table.getTableValue(i, j));
            }
        }
    }

    private void assertEmptyTable(KataminoTable table, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Assert.assertEquals(KataminoTable.EMPTY, table.getTableValue(i, j));
            }
        }
    }
}
