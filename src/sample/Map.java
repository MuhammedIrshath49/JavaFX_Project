package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Map extends Report implements Initializable {

    @FXML
    private WebView mapui;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine engine1 = mapui.getEngine();
        String website = "http://localhost:8383/random/index2.html";
        engine1.load(website);
    }


}
