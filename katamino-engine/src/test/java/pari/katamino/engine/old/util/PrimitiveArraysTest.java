package pari.katamino.engine.old.util;

import org.junit.Assert;
import org.junit.Test;
import pari.katamino.engine.old.util.PrimitiveArrays;

/**
 * Created by xavi on 2016.09.27..
 */
public class PrimitiveArraysTest {
    @Test
    public void joinBooleanArray() throws Exception {
        this.canJoinBooleanArray(new boolean[]{false, true}, ", ", "false, true");
        this.canJoinBooleanArray(new boolean[]{false, true, false}, " ", "false true false");
        this.canJoinBooleanArray(new boolean[0], ", ", "");
    }

    private void canJoinBooleanArray(boolean[] array, String separator, String expectedString) {
        Assert.assertEquals(expectedString, PrimitiveArrays.join(array, separator));
    }

    @Test
    public void joinIntArray() {
        canJoinIntArray(new int[]{1,2}, ", ", "1, 2");
        canJoinIntArray(new int[]{1,2,3}, " ", "1 2 3");
        canJoinIntArray(new int[0], ", ", "");
    }

    private void canJoinIntArray(int[] array, String separator, String expectedString) {
        Assert.assertEquals(expectedString, PrimitiveArrays.join(array, separator));
    }

    @Test
    public void isEmpty() throws Exception {
        this.testIsEmpty(new boolean[]{false, false, false}, true);
        this.testIsEmpty(new boolean[]{false, false, true}, false);
        this.testIsEmpty(new boolean[]{true, true, true}, false);
        this.testIsEmpty(new boolean[0], true);
    }

    private void testIsEmpty(boolean[] array, boolean expectedIsEmpty) {
        Assert.assertEquals(expectedIsEmpty, PrimitiveArrays.isEmpty(array));
    }
}