package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.util.ResourceBundle;

public class Website extends Report implements Initializable {

    @FXML
    WebView webui;

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

        WebEngine engine = webui.getEngine();
         String url1 = "https://eservices.police.gov.sg/content/policehubhome/homepage/police-report.html/";
        engine.load(url1);
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
    }
}
