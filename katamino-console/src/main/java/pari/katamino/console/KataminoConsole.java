package pari.katamino.console;

import pari.katamino.console.commands.ItemsCommand;

/**
 * Created by xavi on 2017.01.01..
 */
public class KataminoConsole {
    public static void main(String[] args) throws Exception {
        CommandRunner runner =  new CommandRunner();

        runner.registerCommand(ItemsCommand.class);

        runner.runCommand(args);
    }
}
