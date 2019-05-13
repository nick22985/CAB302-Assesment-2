package VectorDesignTool;

import VectorDesignTool.vecRead.vecLoad;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // TODO: 13/05/2019 Graphic Interface
        Parent root = FXMLLoader.load(getClass().getResource("VectorDesignTool.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

//        ArrayList command = vecLoad.LoadVecFile("Line.vec");
//
//        for(int i = 0; i< command.size(); i++) {
//            System.out.println(command.get(i));
//        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
