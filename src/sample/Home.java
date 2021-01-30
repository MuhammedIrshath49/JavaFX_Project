package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Home {

    @FXML
    public static Button lbl_close;
    @FXML
    public static Button lbl_min;
    @FXML
    public static Button lbl_max;
    @FXML
    private Button previous_detect;
    @FXML
    public Button logout;
    @FXML
    private Button detect;
    @FXML
    private Button report;

    @FXML
    public void closeAction(MouseEvent event) {
        if (event.getSource() == lbl_close) {
            System.exit(0);
        }
    }

    @FXML
    public void minAction(MouseEvent event) {
        if (event.getSource() == lbl_min) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setIconified(true);
        }
    }

    @FXML
    public void maxAction(MouseEvent event) {
        if (event.getSource() == lbl_max) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setFullScreenExitHint(" ");
            stage.setFullScreen(true);
        }
    }

    @FXML
    public void logoutAction(MouseEvent event) throws IOException {
        if (event.getSource() == logout) {

            JOptionPane.showMessageDialog(null,"You have been logged out successfully!","Logout",JOptionPane.YES_OPTION);
            Parent root = FXMLLoader.load(getClass().getResource("User Authentication.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    public void detectAction(MouseEvent event) throws IOException {
        if (event.getSource() == detect) {
            Parent root = FXMLLoader.load(getClass().getResource("Detect.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    public void reportAction(MouseEvent event) throws IOException {
        if (event.getSource() == report) {
            Parent root = FXMLLoader.load(getClass().getResource("Report.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    public void previousDetectAction(MouseEvent event) throws IOException {
        if(event.getSource() == previous_detect) {
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

}