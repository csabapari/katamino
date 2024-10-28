package pari.katamino.engine.old;

import pari.katamino.engine.old.model.KataminoItem;
import pari.katamino.engine.old.model.KataminoTable;
import pari.katamino.engine.old.model.Step;

import java.util.List;

/**
 * Created by xavi on 2016.11.06..
 */
public interface SolutionHunter {
    List<Step> findSolution(KataminoTable table, List<KataminoItem> items);
}
