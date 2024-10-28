package pari.katamino.engine.old;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pari.katamino.engine.old.model.KataminoItem;
import pari.katamino.engine.old.model.KataminoTable;
import pari.katamino.engine.old.model.Step;
import pari.katamino.engine.old.model.TurnDegrees;

import java.util.*;

/**
 * Created by xavi on 2016.11.06..
 */
public class BreadthFirstHunter implements SolutionHunter {

    private static final Logger LOGGER = LogManager.getLogger(BreadthFirstHunter.class);

    private Queue<Step> stepQueue;

    public BreadthFirstHunter() {
        this.stepQueue = new ArrayDeque<>();
    }

    @Override
    public List<Step> findSolution(KataminoTable table, List<KataminoItem> items) {

        if(table == null) {
            throw new IllegalArgumentException("table cannot be null");
        }

        if(items.size() != items.stream().map(i -> i.getId()).distinct().count()) {
            throw new IllegalArgumentException("items can only contain items with different id.");
        }

        // generate the complete item list with all possible rotation
        List<KataminoItem> itemsInAllPosition = new ArrayList<>();
        for (KataminoItem item: items) {
            Collections.addAll(itemsInAllPosition, item, item.rotate(TurnDegrees.Ninety), item.rotate(TurnDegrees.HundredEighty), item.rotate(TurnDegrees.TwoHundredSeventy));
            KataminoItem mirrored = item.mirror();
            Collections.addAll(itemsInAllPosition, mirrored, mirrored.rotate(TurnDegrees.Ninety), mirrored.rotate(TurnDegrees.HundredEighty), mirrored.rotate(TurnDegrees.TwoHundredSeventy));
        }

        Step root = new Step(table, itemsInAllPosition);
        stepQueue.add(root);

        while (true) {
            Step step = this.stepQueue.poll();
            if(step == null) {
                // no more stepQueue and no solution is found
                LOGGER.debug("Solution is not found!");
                return null;
            }

            // extend the step
            List<Step> nextSteps = step.extend();

            for (Step nextStep: nextSteps) {
                if(nextStep.isLastStep()) {
                    // solution is found!
                    List<Step> solution = this.collectSolutionStepList(nextStep);
                    LOGGER.debug("Solution is found!" + solution);
                    return solution;
                }
            }

            this.stepQueue.addAll(nextSteps);
        }
    }

    private List<Step> collectSolutionStepList(Step lastStep) {
        List<Step> solution = new ArrayList<>();
        Step step = lastStep;
        while(step != null) {
            solution.add(0, step);
            step = step.getPrevious();
        }

        return solution;
    }
}
