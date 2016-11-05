package pari.katamino.engine.util;

/**
 * Created by xavi on 2016.09.27..
 */
public class PrimitiveArrays {

    public static String join(boolean[] array, String separator) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if(i < array.length - 1) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    public static String join(int[] array, String separator){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if(i < array.length - 1) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    public static boolean isEmpty(boolean[] array) {
        for (int i = 0; i < array.length; i++) {
            if(array[i]) {
                return false;
            }
        }
        return true;
    }
}
