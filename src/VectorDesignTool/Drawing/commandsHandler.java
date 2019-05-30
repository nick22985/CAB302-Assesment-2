package VectorDesignTool.Drawing;

import VectorDesignTool.errorHandler.tryParse;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

import static VectorDesignTool.vecRead.fileClass.lengthof2ndDimentioofCommandArray;

public class commandsHandler {
    /**
     *
     * @param gc
     * @param command
     */
    public static void commandsHandler(GraphicsContext gc, ArrayList[][] command) {
        fillColorClass.fillTrue = false;
        ChangePenColour(gc, Color.BLACK);
        for (int i = 0; i < command.length; i++) {
            ArrayList[] currentCommand = command[i];
            whatToDraw(gc, currentCommand);
        }
    }


    /**
     *
     * @param gc
     * @param currentCommand
     */
    public static void whatToDraw(GraphicsContext gc, ArrayList[] currentCommand) {
        String type = currentCommand[0].toString().replaceAll("\\[", "")
                .replaceAll("\\]", "");
        if (type.equals("LINE") || type.equals("RECTANGLE") || type.equals("PLOT") || type.equals("ELLIPSE")) {
            tryParse.tryParseDouble(currentCommand[1]);
            double x1 = Double.parseDouble(currentCommand[1].toString().replaceAll("\\[", "")
                    .replaceAll("\\]", ""));
            double y1 = Double.parseDouble(currentCommand[2].toString().replaceAll("\\[", "")
                    .replaceAll("\\]", ""));
            if (type.equals("LINE") || type.equals("RECTANGLE") || type.equals("ELLIPSE")) {
                double x2 = Double.parseDouble(currentCommand[3].toString().replaceAll("\\[", "")
                        .replaceAll("\\]", ""));
                double y2 = Double.parseDouble(currentCommand[4].toString().replaceAll("\\[", "")
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
            String color = currentCommand[1].toString().replaceAll("\\[", "")
                    .replaceAll("\\]", "");
            if (type.equals("PEN")) {
                ChangePenColour(gc, Color.web(color));
            }
            if (type.equals("FILL")) {
                ChangeFillColour(color);
            }
        }
        if (type.equals("POLYGON")) {
            DrawPolygon(gc, currentCommand);
        }
    }

    /**
     *
     * @param gc Graphics Context
     * @param x1 position of x1
     * @param y1 position of y1
     * @param x2 position of x2
     * @param y2 position of y2
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
     * @param gc Graphics Context
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
     * @param gc Graphics Context
     * @param x1 position of x1
     * @param y1 position of y1
     * @param x2 position of x2
     * @param y2 position of y2
     */
    public static void DrawRec(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        double x = gc.getCanvas().getHeight();
        double y = gc.getCanvas().getWidth();
        if(fillColorClass.fillTrue) {
            gc.setFill(fillColorClass.color);
            gc.fillRoundRect(x1 * x, y1 * y, (x2 - x1) * x, (y2 - y1) * y, 0, 0);
        }
        gc.strokeRect(x1 * x, y1 * y, (x2 - x1) * x, (y2 - y1) * y);
    }

    /**
     *
     * @param gc Graphics Context
     * @param color Pen Color to change to
     */
    public static void ChangePenColour(GraphicsContext gc, Color color) {
        gc.setStroke(color);
    }

    /**
     *
     * @param gc Graphics Context
     * @param x1 position of x1
     * @param y1 position of y1
     * @param x2 position of x2
     * @param y2 position of y2
     */
    public static void DrawEllipse(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        double x = gc.getCanvas().getHeight();
        double y = gc.getCanvas().getWidth();
        if(fillColorClass.fillTrue) {
            gc.setFill(fillColorClass.color);
            gc.fillOval(x1 * x, y1 * y, (x2 - x1) * x, (y2 - y1) * y);
        }
        else {
            gc.strokeOval(x1 * x, y1 * y, (x2 - x1) * x, (y2 - y1) * y);
        }

    }

    /**
     *
     * @param gc
     * @param command
     */
    public static void DrawPolygon(GraphicsContext gc, ArrayList[] command) {
//        gc.strokePolygon(command, command, 1);
//       gc.fillPolygon();
    }

    /**
     *
     * @param color
     */
    public static void ChangeFillColour(String color) {
        if (color.equals("OFF")) {
            fillColorClass.fillTrue = false;
        }
        else {
            fillColorClass.setFill(true);
            fillColorClass.setColor(Color.web(color));
        }
    }
}
