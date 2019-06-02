package VectorDesignTool.Drawing;

import javafx.scene.paint.Color;

public class fillColorClass {
    public static boolean fillTrue;
    public static Color color = Color.BLACK;
    public static Color fillColor;

    /**+
     * @param newColor The new color you want to set your pen to (Global VAR)
     */
    public static void setColor(Color newColor) {
        color = newColor;
    }

    /**
     *
     * @param newfillColor The new fill color you want to set the fill to (Global VAR)
     */
    public static void setFillColor(Color newfillColor) {
        fillColor = newfillColor;
    }

    /**
     *
     * @param fillIsTrue This will turn fill on (Global VAR)
     */
    public static void setFill(boolean fillIsTrue) {
        fillTrue = fillIsTrue;
    }


}
