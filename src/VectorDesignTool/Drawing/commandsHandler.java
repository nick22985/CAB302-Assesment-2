package VectorDesignTool.Drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class commandsHandler {

    public static void commandsHandler(GraphicsContext gc, ArrayList[][] command) {
        for (int i = 0; i < command.length; i++) {
            String type = command[i][0].toString().replaceAll("\\[", "")
                    .replaceAll("\\]", "");
            if (type.equals("LINE") || type.equals("RECTANGLE") || type.equals("PLOT") || type.equals("ELLIPSE")) {
                double x1 = Double.parseDouble(command[i][1].toString().replaceAll("\\[", "")
                        .replaceAll("\\]", ""));
                double y1 = Double.parseDouble(command[i][2].toString().replaceAll("\\[", "")
                        .replaceAll("\\]", ""));
                if (type.equals("LINE") || type.equals("RECTANGLE") || type.equals("ELLIPSE")) {
                    double x2 = Double.parseDouble(command[i][3].toString().replaceAll("\\[", "")
                            .replaceAll("\\]", ""));
                    double y2 = Double.parseDouble(command[i][4].toString().replaceAll("\\[", "")
                            .replaceAll("\\]", ""));
                    if (type.equals("LINE")) {
                        DrawLine(gc, x1, y1, x2, y2);
                    }
                    if (type.equals("RECTANGLE")) {
                        DrawRec(gc, x1, y1, x2, y2);
                    }
                    if (type.equals("ELLIPSE")) {
                        DrawEllipse(gc, x1, y1, x2, y2);
                    }
                }
                if (type.equals("PLOT")) {
                    DrawPlot(gc, x1, y1);
                }
            }
            if (type.equals("PEN") || type.equals("FILL")) {
                String color = command[i][1].toString().replaceAll("\\[", "")
                        .replaceAll("\\]", "");
                if (type.equals("PEN")) {
                    ChangePenColour(gc, Color.web(color));
                }
                if (type.equals("FILL")) {
                    ChangeFillColour(Color.web(color));
                }
            }
            else {

            }
        }
    }

    /**
     *
     * @param gc
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public static void DrawLine(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        double x = gc.getCanvas().getHeight();
        double y = gc.getCanvas().getWidth();
        gc.beginPath();
        gc.moveTo(x1 * x / 1, y1 * x / 1);
        gc.lineTo(x2 * y / 1, y2 * y / 1);
        gc.stroke();
    }

    /**
     *
     * @param gc
     * @param x1
     * @param y1
     */
    public static void DrawPlot(GraphicsContext gc, double x1, double y1) {
        double x = gc.getCanvas().getHeight();
        double y = gc.getCanvas().getWidth();
        gc.beginPath();
        gc.moveTo(x1 * x / 1, y1 * y / 1);
        gc.lineTo((x1 * x / 1), (y1 * y / 1));
        gc.stroke();
    }

    /**
     *
     * @param gc
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public static void DrawRec(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        double x = gc.getCanvas().getHeight();
        double y = gc.getCanvas().getWidth();

        gc.strokeRect(x1 * x / 2, y1 * y / 2, x2 * x / 2, y2 * y / 2);
        gc.setFill(Color.RED);
        gc.fillRoundRect(x1 * x / 2, y1 * y / 2, x2 * x /2, y2 * y /2, 0, 0);
//        gc.beginPath();
//        gc.moveTo(x1 * x / 1, y1 * y / 1);
//        gc.lineTo(x1 * x / 1, y2 * y / 1);
//        gc.lineTo(x2 * x / 1, y2 * y / 1);
//        gc.lineTo(x2 * x / 1, y1 * y / 1);
//        gc.lineTo(x1 * x / 1, y1 * y / 1);

        gc.stroke();
    }

    /**
     *
     * @param gc
     * @param color
     */
    public static void ChangePenColour(GraphicsContext gc, Color color) {
        gc.setStroke(color);
    }

    /**
     *
     * @param gc
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public static void DrawEllipse(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        double x = gc.getCanvas().getHeight();
        double y = gc.getCanvas().getWidth();
        gc.strokeOval(x1 * x, y1 * y, x2 * x, y2 * y);
    }

    public static void ChangeFillColour(Color color) {
        fill
    }


}
