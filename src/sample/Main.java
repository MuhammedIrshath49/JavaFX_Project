package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application  {


    @Override
    public void start(Stage primaryStage) {
        try {
            //For User Page
            Parent root = FXMLLoader.load(getClass().getResource("User Authentication.fxml"));
            Scene scene = new Scene(root);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("User Authentication");
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
