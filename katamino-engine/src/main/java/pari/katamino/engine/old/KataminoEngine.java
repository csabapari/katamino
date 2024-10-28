package pari.katamino.engine.old;

import pari.katamino.engine.old.model.KataminoItem;
import pari.katamino.engine.old.model.KataminoTable;
import pari.katamino.engine.old.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xavi on 2016.09.26..
 */
public class KataminoEngine {

    private SolutionHunter solutionHunter;

    private List<KataminoItem> kataminoItems;

    /**
     * Creates an engine instance with generating the possible katamino items and the used solution hunter.
     */
    public KataminoEngine(SolutionHunter solutionHunter) {
        this.solutionHunter = solutionHunter;
        this.kataminoItems = this.generateItems();
    }

    /**
     * Returns all possible katamino items that can be used.
     */
    public List<KataminoItem> getAllKataminoItem() {
        return this.kataminoItems;
    }

    /**
     * Tries to solve the given size of the table with the selected katamino items
     * @param rows Number of rows the table should have.
     * @param columns Number of columns the table should have.
     * @param itemIds The list of selected item ids.
     * @return The Step list of the solution. null if no solution is found.
     */
    public List<Step> solveTable(int rows, int columns, List<Integer> itemIds) {
        KataminoTable kataminoTable = new KataminoTable(rows, columns);
        List<KataminoItem> selectedItems = kataminoItems.stream().filter(i -> itemIds.contains(i.getId())).collect(Collectors.toList());

        return this.solutionHunter.findSolution(kataminoTable, selectedItems);
    }

    /**
     * Creates all the possible items.
     */
    private List<KataminoItem> generateItems() {
        List<KataminoItem> items = new ArrayList<>();

        // 0001
        // 1111
        items.add(new KataminoItem(
                1,
                new boolean[][]{
                    {false, false, false, true},
                    {true, true, true, true}}));

        // 001
        // 001
        // 111
        items.add(new KataminoItem(
                2,
                new boolean[][]{
                        {false, false, true},
                        {false, false, true},
                        {true, true, true}}));

        // 101
        // 111
        items.add(new KataminoItem(
                3,
                new boolean[][]{
                        {true, false, true},
                        {true, true, true}}));

        // 11111
        items.add(new KataminoItem(
                4,
                new boolean[][]{{true, true, true, true, true}}));

        // 001
        // 111
        // 010
        items.add(new KataminoItem(
                5,
                new boolean[][]{
                        {false, false, true},
                        {true, true, true},
                        {false, true,  false}}));

        // 1110
        // 0011
        items.add(new KataminoItem(
                6,
                new boolean[][]{
                        {true, true, true, false},
                        {false, false, true, true}}));

        // 010
        // 111
        // 010
        items.add(new KataminoItem(
                7,
                new boolean[][]{
                        {false, true, false},
                        {true, true, true},
                        {false, true, false}}));

        // 111
        // 010
        // 010
        items.add(new KataminoItem(
                8,
                new boolean[][]{
                        {true, true, true},
                        {false, true, false},
                        {false, true, false}}));

        // 001
        // 011
        // 110
        items.add(new KataminoItem(
                9,
                new boolean[][]{
                        {false, false, true},
                        {false, true, true},
                        {true, true, false}}));

        // 111
        // 110
        items.add(new KataminoItem(
                10,
                new boolean[][]{
                        {true, true, true},
                        {true, true, false}}));

        // 001
        // 111
        // 100
        items.add(new KataminoItem(
                11,
                new boolean[][]{
                        {false, false, true},
                        {true, true, true},
                        {true, false, false}}));

        // 1111
        // 0100
        items.add(new KataminoItem(
                12,
                new boolean[][]{
                        {true, true, true, true},
                        {false, true, false, false}}));

        return items;
    }
}
