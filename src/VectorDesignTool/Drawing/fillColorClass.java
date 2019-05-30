package VectorDesignTool.Drawing;

import javafx.scene.paint.Color;

public class fillColorClass {
    public static boolean fillTrue;
    public static Color color;
    public static Color fillColor;

    public static void setColor(Color newColor) {
        color = newColor;
    }
    
    public static void setFillColor(Color newfillColor) {
        fillColor = newfillColor;
    }

    public static void setFill(boolean fillIsTrue) {
        fillTrue = fillIsTrue;
    }


}
