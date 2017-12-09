package pari.katamino.console.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pari.katamino.console.Command;
import pari.katamino.console.CommandInfo;

/**
 * Created by xavi on 2017.01.02..
 */
@CommandInfo(name = "items", parametersClassName = NoParameter.class)
public class ItemsCommand extends Command<NoParameter> {

    private static final Logger LOGGER = LogManager.getLogger(ItemsCommand.class);

    public ItemsCommand(NoParameter parameters) {
         super(parameters);
    }

    public boolean run() {
        LOGGER.info("Executing Items Command.");
        return true;
    }
}
