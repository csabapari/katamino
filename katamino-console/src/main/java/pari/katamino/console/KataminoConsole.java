package pari.katamino.console;

import pari.katamino.console.commands.ItemsCommand;

import java.io.Console;

/**
 * Created by xavi on 2017.01.01..
 */
public class KataminoConsole {
    public static void main(String[] args) throws Exception {

        CommandRunner runner = new CommandRunner();
        runner.registerCommand(ItemsCommand.class);
        Console console = System.console();

        while (true)
        {
            String command = console.readLine(">");

            boolean result = runner.runCommand(command);
            System.out.println();
        }


    }
}
