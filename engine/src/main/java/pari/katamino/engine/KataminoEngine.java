package pari.katamino.engine;

import pari.katamino.engine.model.KataminoItem;
import pari.katamino.engine.model.KataminoTable;

import java.util.List;

/**
 * Created by xavi on 2016.09.26..
 */
public class KataminoEngine {

    private SolutionHunter solutionHunter;

    private KataminoTable kataminoTable;

    private List<KataminoItem> kataminoItems;

    public KataminoEngine(SolutionHunter solutionHunter) {
        this.solutionHunter = solutionHunter;
    }
}
