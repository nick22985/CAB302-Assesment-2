package VectorDesignTool;

import VectorDesignTool.vecRead.vecLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VectorDesignController implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    GraphicsContext gc;

    /**
     *
     * @param gc
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
       public void DrawLine(GraphicsContext gc, double x1, double y1, double x2, double y2) {

        double x = gc.getCanvas().getHeight();
        double y = gc.getCanvas().getWidth();

        gc.beginPath();
        gc.moveTo(x1 * x / 1, y1 * x / 1);
        gc.lineTo( x2 * y / 1, y2 * y / 1);
        gc.stroke();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("test");
        gc = canvas.getGraphicsContext2D();

        ArrayList[][] command = vecLoad.LoadVecFile("Line.vec");
        System.out.println(command.length);

        for (int i = 0; i < command.length; i++) {
            double x1 = Double.parseDouble(command[i][1].toString().replaceAll("\\[", "")
                    .replaceAll("\\]", ""));
            double y1 = Double.parseDouble(command[i][2].toString().replaceAll("\\[", "")
                    .replaceAll("\\]", ""));
            double x2 = Double.parseDouble(command[i][3].toString().replaceAll("\\[", "")
                    .replaceAll("\\]", ""));
            double y2 = Double.parseDouble(command[i][4].toString().replaceAll("\\[", "")
                    .replaceAll("\\]", ""));
            DrawLine(gc, x1, y1, x2, y2);
        }



//        gc.setLineWidth(20);
//        gc.setFill(Color.RED);
//        gc.strokeRoundRect(10, 10, 50, 50, 10, 10);
//        // Draw a filled rounded Rectangle
//        gc.fillRoundRect(100, 10, 50, 50, 10, 10);
//        // Change the fill color
//        gc.setFill(Color.BLUE);
//        // Draw an Oval
//        gc.strokeOval(10, 70, 50, 30);
//        // Draw a filled Oval
//        gc.fillOval(100, 70, 50, 30);
//        // Draw a Line
//        gc.strokeLine(200, 50, 300, 50);
//        // Draw an Arc
//        gc.strokeArc(320, 10, 50, 50, 40, 80, ArcType.ROUND);
//        // Draw a filled Arc
//        gc.fillArc(320, 70, 50, 50, 00, 120, ArcType.OPEN);


    }

    public void toolSelected(ActionEvent event) {

    }
}
