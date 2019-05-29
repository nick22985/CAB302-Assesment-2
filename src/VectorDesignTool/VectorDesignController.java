package VectorDesignTool;

import VectorDesignTool.vecRead.vecLoad;
import VectorDesignTool.Drawing.commandsHandler;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VectorDesignController implements Initializable {

    ObservableList<String> choiceBoxList = FXCollections.observableArrayList("Line", "Plot", "Rectangle", "Ellipse", "Polygon");

    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;

    @FXML
    private ChoiceBox choiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String workingDir = System.getProperty("user.dir");
        gc = canvas.getGraphicsContext2D();
        choiceBox.setValue("Line");
        choiceBox.setItems(choiceBoxList);
        ArrayList[][] command = vecLoad.LoadVecFile(workingDir + "/src/vecFiles/Line.vec");
        commandsHandler.commandsHandler(gc, command);
        System.out.println(choiceBox.getValue());
    }

    public void toolSelected(ActionEvent event) {

    }

    public void fileOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(null);
        String test = file.toString();
        System.out.println(test);
        ArrayList[][] command = vecLoad.LoadVecFile(test);
        newFile();
        commandsHandler.commandsHandler(gc, command);
    }

    public void  fileNew() {
        newFile();
    }

    public void newFile() {
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
        gc = canvas.getGraphicsContext2D();
        System.out.println("Canvas Width: " + canvasWidth + " Canvas Height: "+ canvasHeight);
        gc.clearRect(0,0, canvasWidth, canvasHeight);
    }

}
