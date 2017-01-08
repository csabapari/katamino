package pari.katamino.console;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by xavi on 2017.01.02..
 */
@Target(ElementType.FIELD)
public @interface Parameter {

    boolean mandatory() default true;

    String name();
}
