package pari.katamino.console;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pari.katamino.console.commands.ItemsCommand;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xavi on 2017.01.22..
 */
public class CommandRunner {
    private static final Logger LOGGER = LogManager.getLogger(CommandRunner.class);

    private Map<String, Class<? extends Command>> commandMap;
    private Map<String, CommandInfo> commandInfoMap;

    public CommandRunner() {
        this.commandMap = new HashMap<>();
        this.commandInfoMap = new HashMap<>();
    }

    public void registerCommand(Class<? extends Command> commandClass) {
        CommandInfo commandInfo = commandClass.getAnnotation(CommandInfo.class);
        commandMap.put(commandInfo.name(), commandClass);
        commandInfoMap.put(commandInfo.name(), commandInfo);
    }

    public boolean runCommand(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Command command = this.parseCommand(args);

        if(command == null) {
            return false;
        }

        return command.run();
    }

    private Command parseCommand(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if(args == null) {
            throw new IllegalArgumentException("args");
        }

        if(args.length == 0) {
            throw new IllegalArgumentException("args cannot be an empty array");
        }

        String commandName = args[0];

        Class<? extends Command> commandClass = this.commandMap.get(commandName);
        if(commandClass == null) {
            LOGGER.info(String.format("Invalid command name %1!", commandName));
            // TODO exception or null
            return null;
        }

        CommandInfo commandInfo = this.commandInfoMap.get(commandName);
        if(commandInfo == null) {
            throw new IllegalStateException(String.format("The command '%1' does not have CommandInfo registered.", commandName));
        }

        Parameters parameters = this.parseParameters(
                commandInfo.parametersClassName(),
                args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[0]);

        return commandClass.getConstructor(parameters.getClass()).newInstance(parameters);
    }

    private Parameters parseParameters(Class<? extends Parameters> parameterClass, String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return parameterClass.newInstance();
    }
}
