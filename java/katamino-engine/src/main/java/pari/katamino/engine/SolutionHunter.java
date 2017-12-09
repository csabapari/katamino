package pari.katamino.engine;

import pari.katamino.engine.model.KataminoItem;
import pari.katamino.engine.model.KataminoTable;
import pari.katamino.engine.model.Step;

import java.util.List;

/**
 * Created by xavi on 2016.11.06..
 */
public interface SolutionHunter {
    List<Step> findSolution(KataminoTable table, List<KataminoItem> items);
}
