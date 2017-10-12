package pari.katamino.gui;

import pari.katamino.engine.model.KataminoTable;

public interface SolutionListener {
    void started();

    void finished(KataminoTable solution);
}
