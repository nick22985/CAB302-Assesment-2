package VectorDesignTool;

import VectorDesignTool.vecRead.fileClass;
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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VectorDesignController implements Initializable {

    ObservableList<String> shapeSelecterList = FXCollections.observableArrayList("LINE", "PLOT", "RECTANGLE", "ELLIPSE", "POLYGON");

    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;

    @FXML
    private ChoiceBox shapeSelecter;

    @FXML
    private TextField newShape;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String workingDir = System.getProperty("user.dir");
        gc = canvas.getGraphicsContext2D();
        shapeSelecter.setValue("LINE");
        shapeSelecter.setItems(shapeSelecterList);
        fileClass.setFileName(workingDir + "/src/vecFiles/Line.vec");
        ArrayList[][] command = vecLoad.LoadVecFile(workingDir + "/src/vecFiles/Line.vec");
        fileClass.setCommandList(command);
        commandsHandler.commandsHandler(gc, fileClass.commandsList);
        newShape.setText(shapeSelecter.getValue().toString() + " ");
    }

    public void toolSelected(ActionEvent event) {

    }

    public void fileOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(null);
        String fileLocation = file.toString();
        openFile(fileLocation);
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

    public void openFile(String fileLocation) {
        fileClass.setFileName(fileLocation);
        ArrayList[][] command = vecLoad.LoadVecFile(fileLocation);
        fileClass.setCommandList(command);
        newFile();
        commandsHandler.commandsHandler(gc, command);
    }

    public void choiceBoxOnAction(ActionEvent event) {
        newShape.setText(shapeSelecter.getValue().toString() + " ");
    }

    public void createShape(ActionEvent event) {
        fileClass.getCommandListSize();
        fileClass.addCommand(newShape.getText());
//        fileClass.addCommand();
    }
}
