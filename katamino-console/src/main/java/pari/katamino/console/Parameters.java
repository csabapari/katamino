package pari.katamino.console;

/**
 * Created by xavi on 2017.01.01..
 */
public abstract class Parameters {

    public static Parameters parseParameters(String parameterClassName, String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class parameterClass = Class.forName(parameterClassName);

        return (Parameters) parameterClass.newInstance();
    }
}
