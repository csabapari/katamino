package pari.katamino.engine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a tree node during the solution search.
 * Contains the state of the table and previous step (previous node).
 *
 * Created by xavi on 2016.11.06..
 */
public class Step {

    private KataminoTable table;
    private KataminoItem item;
    private List<KataminoItem> nextItems;
    private Step previous;
    private List<Step> nextSteps;

    public Step(KataminoTable table, List<KataminoItem> nextItems) {
        if(table == null) {
            throw new IllegalArgumentException("table");
        }

        this.table = table;
        this.nextItems = nextItems;
        this.nextSteps = new ArrayList<>();
    }

    public Step(Step previous, KataminoTable table, KataminoItem item, List<KataminoItem> nextItems) {
        this(table, nextItems);

        this.item = item;
        this.previous = previous;
    }

    public List<Step> extend() {
        for (KataminoItem nextItem: this.nextItems) {
            // tries to add the nextItem to the table
            KataminoTable nextTable = new KataminoTable(this.table);
            if(nextTable.addItem(nextItem)) {
                // if the item have been added to the table
                // then filter out the item and its rotations from the next possible ones
                List<KataminoItem> nextNextItems = this.nextItems.stream().filter(i -> i.getId() != nextItem.getId()).collect(Collectors.toList());

                this.nextSteps.add(new Step(this, nextTable, nextItem, nextNextItems));
            }
        }

        return this.nextSteps;
    }

    public boolean isLastStep() {
        return this.table.isComplete() && this.nextItems.size() == 0;
    }

    public Step getPrevious() {
        return this.previous;
    }

    @Override
    public String toString() {
        StringBuilder sb =  new StringBuilder();
        String lineSeparator = System.lineSeparator();

        sb.append(lineSeparator);
        sb.append("Step");
        sb.append(lineSeparator);
        sb.append("=====================");
        sb.append(this.item == null ? "" : this.item);
        sb.append(this.table);
        sb.append("=====================");
        sb.append(lineSeparator);

        return sb.toString();
    }
}
