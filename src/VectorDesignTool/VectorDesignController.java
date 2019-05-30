package VectorDesignTool;

import VectorDesignTool.Drawing.fillColorClass;
import VectorDesignTool.vecRead.fileClass;
import VectorDesignTool.vecRead.vecLoad;
import VectorDesignTool.Drawing.commandsHandler;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static VectorDesignTool.vecRead.fileClass.addCommand;

public class VectorDesignController implements Initializable {

    ObservableList<String> shapeSelecterList = FXCollections.observableArrayList("LINE", "PLOT", "RECTANGLE", "ELLIPSE", "POLYGON");

    @FXML private RadioButton rbC1;
    @FXML private RadioButton rbC2;
    @FXML private RadioButton rbC3;
    @FXML private RadioButton rbC4;
    @FXML private RadioButton rbC5;
    @FXML private RadioButton rbC6;
    @FXML private RadioButton rbC7;
    @FXML private RadioButton rbC8;
    @FXML private RadioButton rbC9;
    @FXML private RadioButton rbC10;
    @FXML private RadioButton rbC11;
    @FXML private RadioButton rbC12;
    @FXML private RadioButton rbC13;
    @FXML private RadioButton rbC14;
    @FXML private RadioButton rbC15;
    @FXML private RadioButton rbC16;
    @FXML private RadioButton rbC17;
    @FXML private RadioButton rbC18;
    @FXML private RadioButton pen;
    @FXML private RadioButton fill;
    @FXML private RadioButton tempRadio;


    @FXML private ToggleGroup customColor;
    @FXML private ToggleGroup fillColor;

    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;

    @FXML
    private ChoiceBox shapeSelecter;

    @FXML
    private TextField newShape;
    @FXML
    private CheckBox fileEnableScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialiseColor();
        String workingDir = System.getProperty("user.dir");
        gc = canvas.getGraphicsContext2D();
        shapeSelecter.setValue("LINE");
        shapeSelecter.setItems(shapeSelecterList);
        fileClass.setFileName(workingDir + "/src/vecFiles/Line.vec");
        ArrayList[][] command = vecLoad.LoadVecFile(workingDir + "/src/vecFiles/Line.vec");
        fileClass.setCommandList(command);
        commandsHandler.commandsHandler(gc, fileClass.commandsList);
        newShape.setText(shapeSelecter.getValue().toString() + " 0.400000 0.000000 1.000000 0.400000");
    }

    public void initialiseColor() {
        rbC1.setUserData("255 255 255");
        rbC2.setUserData("195 195 195");
        rbC3.setUserData("88 88 88");
        rbC4.setUserData("0 0 0");
        rbC5.setUserData("136 0 27");
        rbC6.setUserData("236 28 36");
        rbC7.setUserData("255 127 39");
        rbC8.setUserData("255 202 24");
        rbC9.setUserData("253 236 166");
        rbC10.setUserData("255 242 0");
        rbC11.setUserData("196 255 14");
        rbC12.setUserData("14 209 69");
        rbC13.setUserData("140 255 251");
        rbC14.setUserData("0 168 243");
        rbC15.setUserData("63 72 204");
        rbC16.setUserData("184 61 186");
        rbC17.setUserData("255 174 200");
        rbC18.setUserData("185 122 86");
        pen.setUserData("pen");
        pen.setStyle("-fx-background-color: rgba(0, 0, 0)");
        fill.setUserData("fill");
        fill.setStyle("-fx-background-color: rgba(255, 255, 255)");
    }

    public void fileOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String fileLocation = file.toString();
            openFile(fileLocation);
        }
    }

    public void  fileNew() {
        newFile();
    }

    public void newFile() {
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
        gc = canvas.getGraphicsContext2D();
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

    public void selectColor(ActionEvent event) {
        String [] temp = new String[3];
        String [] words = customColor.getSelectedToggle().getUserData().toString().split(" ");
        for (int b = 0; b < words.length; b++) {
            temp[b] = words[b];
        }
        String penFIll = fillColor.getSelectedToggle().getUserData().toString();
        if (penFIll == "pen") {
            tempRadio = pen;
        }
        if (penFIll == "fill") {
            tempRadio = fill;
        }
        tempRadio.setStyle("-fx-background-color: rgba(" + temp[0] + ", " + temp[1] + ", " + temp[2] + ")");
    }

    public void createShape(ActionEvent event) {
        fileClass.getCommandListSize();
        if (fileEnableScene.isSelected()) {
            String rgbValue = "";
            String REGEX = "\\((.*?)\\)";
            String INPUT = fill.getStyle();

            Pattern p = Pattern.compile(REGEX);

            Matcher m = p.matcher(INPUT);
            if (m.find()) {
                rgbValue = m.group(1);
            }
            String [] splitRGBValue = rgbValue.split(", ");
            int [] rgbtoint = new int [4];
            for (int a = 0; a < splitRGBValue.length; a++) {
                rgbtoint[a] = Integer.parseInt(splitRGBValue[a]);
            }

            Color RGBColor = Color.rgb(rgbtoint[0], rgbtoint[1], rgbtoint[2]);
            fillColorClass.setFill(true);
            if (fillColorClass.fillTrue) {
                String temp = RGBColor.toString();
                Pattern k = Pattern.compile("^\\w{2}");
                Matcher l = k.matcher(temp);
                StringBuffer sb = new StringBuffer();
                while (l.find()) {
                    l.appendReplacement(sb, "");
                }
                l.appendTail(sb);
                temp = sb.toString();
                Pattern n = Pattern.compile("^\\w{6}");
                Matcher u = n.matcher(temp);
                if (u.find()) {
                    temp = u.group(0);
                }
                temp = "FILL #" + temp;
                addCommand(temp, gc);
            }
        }
        if (!fileEnableScene.isSelected()) {
            if (fillColorClass.fillTrue) {
                fillColorClass.setFill(false);
                addCommand("FILL OFF", gc);
            }
        }

        addCommand(newShape.getText(), gc);
    }
}
