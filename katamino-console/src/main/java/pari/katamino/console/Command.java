package pari.katamino.console;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xavi on 2017.01.01..
 */
public abstract class Command<T extends Parameters> {

    private T parameters;

    public Command(T parameters) {
        this.parameters = parameters;
    }

    protected T getParameters() {
        return this.parameters;
    }

    public abstract boolean run();
}
