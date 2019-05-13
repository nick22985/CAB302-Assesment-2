package VectorDesignTool;

import VectorDesignTool.vecRead.vecLoad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        vecLoad.LoadVecFile("Line.vec");
        // TODO: 13/05/2019 Graphic Interface
        Parent root = FXMLLoader.load(getClass().getResource("VectorDesignTool.fxml"));
        primaryStage.setTitle("Vector Design");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
