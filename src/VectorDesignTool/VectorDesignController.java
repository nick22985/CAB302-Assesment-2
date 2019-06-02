package VectorDesignTool;

import VectorDesignTool.Drawing.fillColorClass;
import VectorDesignTool.vecRead.fileClass;
import VectorDesignTool.vecRead.vecLoad;
import VectorDesignTool.Drawing.commandsHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static VectorDesignTool.vecRead.fileClass.*;

public class VectorDesignController implements Initializable {

    private ObservableList<String> shapeSelecterList = FXCollections.observableArrayList("LINE", "PLOT", "RECTANGLE", "ELLIPSE", "POLYGON");
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
    @FXML private Canvas canvas;
    @FXML private GraphicsContext gc;
    @FXML private ChoiceBox shapeSelecter;
    @FXML private TextField newShape;
    @FXML private CheckBox fileEnableScene;
    @FXML private TextArea vectorCommandTextArea;
    @FXML private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialiseColor();
        String workingDir = System.getProperty("user.dir");
        gc = canvas.getGraphicsContext2D();
        shapeSelecter.setValue("LINE");
        shapeSelecter.setItems(shapeSelecterList);
        commandsList = new ArrayList[1][500];
        newShape.setText(shapeSelecter.getValue().toString() + " 0.400000 0.000000 1.000000 0.400000");
        setTextArea(commandsList, 0, 0);
        setTextBox();
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
        pen.setUserData("PEN");
        pen.setStyle("-fx-background-color: rgba(0, 0, 0)");
        fill.setUserData("FILL");
        fill.setStyle("-fx-background-color: rgba(255, 255, 255)");
    }

    private void setTextBox() {
        vectorCommandTextArea.setText(commandTextBox);
        vectorCommandTextArea.selectPositionCaret(vectorCommandTextArea.getLength());
        vectorCommandTextArea.deselect();
    }
    double firstX = -1;
    double firstY= -1;
    double LastX;
    double LastY;

    public void getObjectDraw(MouseEvent event) {
        if (firstX == -1) {
            firstX = event.getSceneX();
        }
        if (firstY == -1) {
            firstY = event.getSceneY();
        }

        LastX = event.getSceneX();
        LastY = event.getSceneY();
        System.out.println(firstX);
        System.out.println(firstY);
        System.out.println(LastX);
        System.out.println(LastY);

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

    public void fileNew() {
        newFile();
    }

    private void newFile() {
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
        resetDefault();
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvasWidth, canvasHeight);
        setTextBox();
    }


    private void openFile(String fileLocation) {
        fileClass.setFileName(fileLocation);
        System.out.print(fileLocation);
        newFile();
        fileClass.setCommandList(vecLoad.LoadVecFile(fileLocation));
        commandsHandler.commandsHandler(gc, commandsList);
        setTextArea(commandsList, 0, 0);
        setTextBox();

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
        if (penFIll.equals("PEN")) {
            tempRadio = pen;
        }
        if (penFIll.equals("FILL")) {
            tempRadio = fill;
        }
        tempRadio.setStyle("-fx-background-color: rgba(" + temp[0] + ", " + temp[1] + ", " + temp[2] + ")");
    }

    public void createShape(ActionEvent event) {
        fileClass.getCommandListSize();
        if (fileEnableScene.isSelected()) {
            if (!fillColorClass.fillTrue) {
                String temp = getRGBValueCommandString(fill);
                addCommand(temp, gc);
            }
            fillColorClass.setFill(true);
            String currentPen = getRGBColor(fill).toString();
            if(!fillColorClass.fillColor.toString().equals(currentPen)) {
                String temp = getRGBValueCommandString(fill);
                addCommand(temp, gc);
            }

        }
        if (!fileEnableScene.isSelected()) {
            if (fillColorClass.fillTrue) {
                fillColorClass.setFill(false);
                addCommand("FILL OFF", gc);
            }
        }

        Color CurrentPen = getRGBColor(pen);
        if (!CurrentPen.equals(fillColorClass.color)) {
            addCommand(getRGBValueCommandString(pen), gc);
        }
        addCommand(newShape.getText(), gc);
        setTextBox();
    }


    /**
     *
     * @param fillpen the type of radio button to get the color from
     * @return it returns the color from radio button as a color
     */
    private String getRGBValueCommandString(RadioButton fillpen) {
        String temp = regexReplace("^\\w{2}", getRGBColor(fillpen).toString(), "");
        temp = regexFind("^\\w{6}", temp, 0);
        return fillpen.getUserData() +  " #" + temp;
    }

    private Color getRGBColor(RadioButton fillpen) {
        String [] splitRGBValue = regexFind("\\((.*?)\\)", fillpen.getStyle(), 1).split(", ");
        int [] rgbtoint = new int [4];
        for (int a = 0; a < splitRGBValue.length; a++) {
            rgbtoint[a] = Integer.parseInt(splitRGBValue[a]);
        }
        return Color.rgb(rgbtoint[0], rgbtoint[1], rgbtoint[2]);
    }



    /**
     *
     * @param REGEX REGEX string to match values
     * @param INPUT Input string to match to
     * @param MatcherType the type of match you want Matcher to use
     * @return returns with the regex found Regex statment
     */
    private String regexFind(String REGEX, String INPUT, int MatcherType) {
        String regexString = "";
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);
        if (m.find()) {
            regexString = m.group(MatcherType);
        }
        return regexString;
    }

    /**
     *
     * @param REGEX REGEX string to match values
     * @param INPUT Input string to match to
     * @param replacement replacement value for what the regex matches
     * @return the regex strings
     */
    public static String regexReplace(String REGEX, String INPUT, String replacement) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, replacement);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public void fileSaveT(ActionEvent event) throws IOException {
        saveFile();
    }

    public static String fileSaveOpen() {
        String fileLocation = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            fileLocation = file.toString();
        }
        return fileLocation;
    }


    public static void saveFile() throws IOException {
        String fileContent = "";
        String saveLocation = "";
        for (int a = 0; a < commandlistSize; a++) {
            for (int b = 0; b < lengthof2ndDimentioofCommandArray[a]; b++) {
                String temp = commandsList[a][b].toString().replace("[", "");
                temp = temp.replace("]", "");
                fileContent += temp + " ";
            }
            fileContent += "\n";
        }
        if (fileName.equals("")) {
            saveLocation = fileSaveOpen();
        }
        else {
            saveLocation = fileName;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(saveLocation));
        writer.write(fileContent);
        writer.close();

    }


}
