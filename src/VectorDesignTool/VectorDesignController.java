package VectorDesignTool;

import VectorDesignTool.vecRead.vecLoad;
import VectorDesignTool.Drawing.commandsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VectorDesignController implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        ArrayList[][] command = vecLoad.LoadVecFile("Line.vec");
        commandsHandler.commandsHandler(gc, command);
    }

    public void toolSelected(ActionEvent event) {

    }
}
