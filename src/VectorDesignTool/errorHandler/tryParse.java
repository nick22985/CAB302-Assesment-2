package VectorDesignTool.errorHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class tryParse {

    public static Boolean tryParseDouble(ArrayList array) {
        try {
           Double.parseDouble(array.toString().replaceAll("\\[", "")
                    .replaceAll("\\]", ""));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean tryParseString(ArrayList array) {
        try {
            array.toString().replaceAll("\\[", "")
                    .replaceAll("\\]", "");
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
