package pari.katamino.console;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by xavi on 2017.01.02..
 */
@Target(ElementType.TYPE)
public @interface CommandInfo {
    String parametersClassName();
}
