package VectorDesignTool;

import VectorDesignTool.vecRead.vecLoad;
import VectorDesignTool.Drawing.commandsHandler;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();

        ArrayList[][] command = vecLoad.LoadVecFile("Line.vec");

        commandsHandler.commandsHandler(gc, command);
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
