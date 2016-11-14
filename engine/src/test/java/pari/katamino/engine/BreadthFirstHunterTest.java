package pari.katamino.engine;

import org.junit.Assert;
import org.junit.Test;
import pari.katamino.engine.model.KataminoItem;
import pari.katamino.engine.model.KataminoTable;
import pari.katamino.engine.model.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xavi on 2016.11.12..
 */
public class BreadthFirstHunterTest {
    @Test
    public void canFindSolution() {
        BreadthFirstHunter hunter = new BreadthFirstHunter();

        final int rows = 3;
        final int columns = 5;

        KataminoTable table = new KataminoTable(rows, columns);
        KataminoItem item1 = new KataminoItem(1, new boolean[][] {{true, true, true, true}, {true, false, false, false}});
        KataminoItem item2 = new KataminoItem(2, new boolean[][]{{false, true, true, true}, {true, true, false, false}});
        KataminoItem item3 = new KataminoItem(3, new boolean[][]{{false, false, true}, {false, false, true}, {true, true, true}});
        List<KataminoItem> items = new ArrayList<>();
        Collections.addAll(items, item1, item2, item3);

        List<Step> solution = hunter.findSolution(table, items);

        // 3 + 1 because the first step is the empty table
        Assert.assertEquals(4, solution.size());
    }

    @Test
    public void canFindSolutionWhenItemRotationRequired() {
        BreadthFirstHunter hunter = new BreadthFirstHunter();

        final int rows = 3;
        final int columns = 5;

        KataminoTable table = new KataminoTable(rows, columns);
        KataminoItem item1 = new KataminoItem(1, new boolean[][] {{true, true}, {false, true}, {false, true}, {false, true}});
        KataminoItem item2 = new KataminoItem(2, new boolean[][]{{false, true, true, true}, {true, true, false, false}});
        KataminoItem item3 = new KataminoItem(3, new boolean[][]{{false, false, true}, {false, false, true}, {true, true, true}});
        List<KataminoItem> items = new ArrayList<>();
        Collections.addAll(items, item1, item2, item3);

        List<Step> solution = hunter.findSolution(table, items);

        // 3 + 1 because the first step is the empty table
        Assert.assertEquals(4, solution.size());
    }
}
