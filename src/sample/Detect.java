package sample;

import javafx.application.HostServices;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javafx.scene.image.ImageView ;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



public class Detect extends Home  {


    @FXML
    private Button detectReport;
    @FXML
    private Button detectButton;
    @FXML
    private Button uploadButton;
    @FXML
    ImageView showtime;

    String website = "http://localhost:8383/random/index.html";


    private static HostServices hostServices;

    public static HostServices getHostServices() {
        return hostServices;
    }

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
    public void detectReport(MouseEvent event) throws IOException {
        if (event.getSource() == detectReport) {
            Parent root = FXMLLoader.load(getClass().getResource("Report.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    public void detectAction(MouseEvent event) {
        if (event.getSource() == detectButton) {
            try {
                Desktop.getDesktop().browse(new URL(website).toURI());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void uploadImage(MouseEvent event) throws IOException {

        if (event.getSource() == uploadButton) {
            FileChooser fc = new FileChooser();
            // FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
            FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
            fc.getExtensionFilters().addAll(ext2);
            File file = fc.showOpenDialog(uploadButton.getScene().getWindow());


                BufferedImage bufferedImage = ImageIO.read(file);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                showtime.setImage(image);
                FileInputStream fin = new FileInputStream(file);
                int len = (int)file.length();
        }
    }

    @FXML
    public void saveImage (MouseEvent event) throws SQLException,IOException {
        Connection connection = DBConnect.getInstance().getConnection();

        Image detectimage = showtime.getImage();

        try {
            Statement statement = connection.createStatement();
            int status1 = statement.executeUpdate("insert into detect (images) " + " values ('" + detectimage + "')");
            if (status1 > 0) {
                JOptionPane.showMessageDialog(null, "Image has been submitted!", "Image Submission", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showMessageDialog(null, "Please review your information before submitting!", "Submission Denied", JOptionPane.ERROR_MESSAGE);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @FXML
    public void updateImageAction(MouseEvent event) throws SQLException, IOException {
        Connection connection1 = DBConnect.getInstance().getConnection();

        Image detectimage = showtime.getImage();
        int id = 1;

        Statement statement1 = connection1.createStatement();

        try {
            int status2 = statement1.executeUpdate("update detect set images = '"+detectimage+"' where did = '"+id+"'");
            if (status2 > 0) {
                JOptionPane.showMessageDialog(null, "Image has been updated!", "Report Update", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showMessageDialog(null,"Please review your information before submitting!","Submission Denied",JOptionPane.ERROR_MESSAGE);
            }
            connection1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection1.close();
        }
    }
}

