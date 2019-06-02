package VectorDesignTool.Drawing;

import VectorDesignTool.tryParse;
import VectorDesignTool.vecRead.fileClass;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class commandsHandler {
    /**
     *
     * @param gc Graphical Context
     * @param command Command list to execute to make the shapes
     */
    public static void commandsHandler(GraphicsContext gc, ArrayList[][] command) {
        fillColorClass.fillTrue = false;
        ChangeFillColour("OFF");
        ChangePenColour(gc, Color.BLACK);
        gc.setStroke(fillColorClass.color);
        for (int i = 0; i < command.length; i++) {
            ArrayList[] currentCommand = command[i];
            fileClass.counterC += 1;
            whatToDraw(gc, currentCommand);
        }
        fileClass.counterC = 0;
    }


    /**
     *
     * @param gc Graphical Context
     * @param currentCommand This is the current commmand that this function will draw
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
     * @param x1 This is position x1
     * @param y1 This is position x2
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
            gc.setFill(fillColorClass.fillColor);
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
        fillColorClass.setColor(color);
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
            gc.setFill(fillColorClass.fillColor);
            gc.fillOval(x1 * x, y1 * y, (x2 - x1) * x, (y2 - y1) * y);
        }
        else {
            gc.strokeOval(x1 * x, y1 * y, (x2 - x1) * x, (y2 - y1) * y);
        }

    }

    /**
     *
     * @param gc Graphical Context
     * @param command This is the command arraylist
     */
    public static void DrawPolygon(GraphicsContext gc, ArrayList[] command) {
        int commandSize = fileClass.lengthof2ndDimentioofCommandArray[fileClass.counterC - 1] - 1;
        double [] x = new double[(commandSize / 2)];
        double [] y = new double[(commandSize / 2)];
        int xsize = 0;
        int ysize = 0;
        double xh = gc.getCanvas().getHeight();
        double yw = gc.getCanvas().getWidth();
        for (int a = 1; a < commandSize + 1; a++) {
            if (command[a] != null) {
                if (a % 2 != 0) {
                    try {
                        x[xsize] = Double.parseDouble(command[a].toString().replaceAll("\\[", "")
                                .replaceAll("\\]", "")) * xh;

                    } catch (NumberFormatException error) {
                        System.out.print(error);

                    }
                    xsize += 1;

                } else {
                    try {
                        y[ysize] = Double.parseDouble(command[a].toString().replaceAll("\\[", "")
                                .replaceAll("\\]", "")) * yw;
                    } catch (NumberFormatException error) {
                        System.out.print(error);
                    }
                    ysize += 1;
                }
            }
        }
        int temp = commandSize / 2;
        if(fillColorClass.fillTrue) {
            gc.setFill(fillColorClass.fillColor);
            gc.fillPolygon(x, y, temp);
        }
        else {
            gc.strokePolygon(x, y, temp);
        }
    }

    /**
     *
     * @param color this is the color you whant to set the fill to or turn it off
     */
    public static void ChangeFillColour(String color) {
        if (color.equals("OFF")) {
            fillColorClass.fillTrue = false;
        }
        else {
            fillColorClass.setFill(true);
            fillColorClass.setFillColor(Color.web(color));
        }
    }
}
