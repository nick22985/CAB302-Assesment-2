package VectorDesignTool;

import java.util.ArrayList;

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