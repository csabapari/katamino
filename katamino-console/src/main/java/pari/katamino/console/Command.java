package pari.katamino.console;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Created by xavi on 2017.01.01..
 */
public abstract class Command<T extends Parameters> {

    private String name;
    private T parameters;

    protected Command(String name, T parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return this.name;
    }

    public T getParameters() {
        return this.parameters;
    }

    public abstract boolean run();

    public static Command parseCommand(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if(args == null) {
            throw new IllegalArgumentException("args");
        }

        if(args.length == 0) {
            throw new IllegalArgumentException("args cannot be an empty array");
        }

        String commandName = args[0];

        Parameters parameters = Parameters.parseParameters(
                commandName,
                args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[0]);

        Class commandClass = Class.forName(commandName.replace(commandName.charAt(0), Character.toUpperCase(commandName.charAt(0))) + "Command");
        return (Command)commandClass.getConstructor(String.class, parameters.getClass()).newInstance(commandName, parameters);
    }
}
