package pari.katamino.gui;

import pari.katamino.engine.BreadthFirstHunter;
import pari.katamino.engine.SolutionHunter;
import pari.katamino.engine.model.KataminoItem;
import pari.katamino.engine.model.KataminoTable;
import pari.katamino.engine.model.Step;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class KataminoSolver extends SwingWorker<KataminoTable, Object> {

    private int numberOfRows;

    private List<KataminoItem> items;

    private SolutionListener listener;

    public KataminoSolver(int numberOfRows, List<KataminoItem> items, SolutionListener listener) {
        this.numberOfRows = numberOfRows;
        this.items = items;
        this.listener = listener;
    }

    @Override
    protected KataminoTable doInBackground() throws Exception {
        this.listener.started();

        SolutionHunter kataminoSolutionHunter = new BreadthFirstHunter();

        List<Step> solutionSteps = kataminoSolutionHunter.findSolution(new KataminoTable(this.numberOfRows, 5), this.items);

        if(solutionSteps == null) {
            return null;
        }

        return solutionSteps.get(solutionSteps.size() - 1).getTable();
    }

    @Override
    protected void done() {
        try {
            this.listener.finished(this.get());
        } catch (InterruptedException e) {
            // TODO
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO
            e.printStackTrace();
        }
    }
}
