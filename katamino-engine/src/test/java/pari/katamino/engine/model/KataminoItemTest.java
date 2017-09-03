package pari.katamino.engine.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xavi on 2016.09.27..
 */
public class KataminoItemTest {

    @Test
    public void canRotateWithZeroDegree() {
        KataminoItem expected = new KataminoItem(1, new boolean[][]{{false, true, false, false}, {true, true, true, true}});

        KataminoItem item =  new KataminoItem(1, new boolean[][]{{false, true, false, false}, {true, true, true, true}});

        KataminoItem rotated = item.rotate(TurnDegrees.Zero);

        Assert.assertTrue(expected.equals(rotated));
    }

    @Test
    public void canRotateWithNinetyDegrees() {
        KataminoItem expected = new KataminoItem(1, new boolean[][]{{true, false}, {true, true}, {true, false}, {true, false}});

        KataminoItem item =  new KataminoItem(1, new boolean[][]{{false, true, false, false}, {true, true, true, true}});

        KataminoItem rotated = item.rotate(TurnDegrees.Ninety);

        Assert.assertTrue(expected.equals(rotated));
    }

    @Test
    public void canRotateWithHundredEightyDegree() {
        KataminoItem expected = new KataminoItem(1, new boolean[][]{{false, true, true, true}, {false, false, true, true}});

        KataminoItem item =  new KataminoItem(1, new boolean[][]{{true, true, false, false}, {true, true, true, false}});

        KataminoItem rotated = item.rotate(TurnDegrees.HundredEighty);

        Assert.assertTrue(expected.equals(rotated));
    }

    @Test
    public void canRotateWithTwoHundredSeventyDegree() {
        KataminoItem expected = new KataminoItem(1, new boolean[][]{{false, true}, {false, true}, {true, true}, {false, true}});

        KataminoItem item =  new KataminoItem(1, new boolean[][]{{false, true, false, false}, {true, true, true, true}});

        KataminoItem rotated = item.rotate(TurnDegrees.TwoHundredSeventy);

        Assert.assertTrue(expected.equals(rotated));
    }

    @Test
    public void canGetMapValue() {
        KataminoItem item =  new KataminoItem(1, new boolean[][]{{false, true, false, false}, {true, true, true, true}});

        Assert.assertEquals(false, item.getMapValue(0, 2));
        Assert.assertEquals(true, item.getMapValue(1, 2));
    }

    @Test
    public void canMirror2x4() {
        KataminoItem expected =  new KataminoItem(1, new boolean[][]{{false, false, true, false}, {true, true, true, true}});

        KataminoItem item =  new KataminoItem(1, new boolean[][]{{false, true, false, false}, {true, true, true, true}});

        KataminoItem mirrored = item.mirror();

        Assert.assertTrue(expected.equals(mirrored));
    }

    @Test
    public void canMirror3x2() {
        KataminoItem expected =  new KataminoItem(1, new boolean[][]{{false, true}, {true, true}, {true, true}});

        KataminoItem item =  new KataminoItem(1, new boolean[][]{{true, false}, {true, true}, {true, true}});

        KataminoItem mirrored = item.mirror();

        Assert.assertTrue(expected.equals(mirrored));
    }

    @Test
    public void canMirror3x3() {
        KataminoItem expected =  new KataminoItem(1, new boolean[][]{{false, false, true}, {false, false, true}, {true, true, true}});

        KataminoItem item =  new KataminoItem(1, new boolean[][]{{true, false, false}, {true, false, false}, {true, true, true}});

        KataminoItem mirrored = item.mirror();

        Assert.assertTrue(expected.equals(mirrored));
    }
}