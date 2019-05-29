package VectorDesignTool.Drawing;

import javafx.scene.paint.Color;

public class fillColorClass {
    public static boolean fillTrue;
    public static Color color;

    public static void setColor(Color newColor) {
        color = newColor;
    }

    public static void setFill(boolean fillIsTrue) {
        fillTrue = fillIsTrue;
    }

}
