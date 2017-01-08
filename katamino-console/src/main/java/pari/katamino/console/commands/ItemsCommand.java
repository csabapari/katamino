package pari.katamino.console.commands;

import pari.katamino.console.Command;
import pari.katamino.console.CommandInfo;

/**
 * Created by xavi on 2017.01.02..
 */
@CommandInfo(parametersClassName = "pari.katamino.console.commands.NoParameter")
public class ItemsCommand extends Command<ItemsParameters> {

    protected ItemsCommand(String name, ItemsParameters parameters) {
        super(name, parameters);
    }

    public boolean run() {
        return false;
    }
}
