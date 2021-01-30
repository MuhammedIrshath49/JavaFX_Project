package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Report extends Home  {

    @FXML
    public Hyperlink link;
    @FXML
    private Button previous_report;
    @FXML
    private Button previous_webview;
    @FXML
    public TextArea description_text;
    @FXML
    public TextField location_text;
    @FXML
    private Button locationButton;
    @FXML
    private Button previous_map;
    String id;

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
    public void webAction(MouseEvent event) throws IOException {
        if (event.getSource() == link) {
            Parent root = FXMLLoader.load(getClass().getResource("website.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }


    @FXML
    public void previousReportAction(MouseEvent event) throws IOException {
        if(event.getSource() == previous_report) {
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }
    @FXML
    public void previousWebviewAction(MouseEvent event) throws IOException {
        if(event.getSource() == previous_webview) {
            Parent root = FXMLLoader.load(getClass().getResource("Report.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }
    @FXML
    public void locationAction(MouseEvent event) throws IOException {
        if(event.getSource() == locationButton) {
            Parent root = FXMLLoader.load(getClass().getResource("map.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }
    @FXML
    public void previousMapAction(MouseEvent event) throws IOException {
        if(event.getSource() == previous_map) {
            Parent root = FXMLLoader.load(getClass().getResource("Report.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    public void submitReport(MouseEvent event) throws SQLException, IOException{
        Connection connection = DBConnect.getInstance().getConnection();

        String description = description_text.getText();
        String location = location_text.getText();

        Statement statement = connection.createStatement();

        try {
            if (description.isEmpty() || location.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Please review your information before submitting!","Submission Denied",JOptionPane.ERROR_MESSAGE);
            } else {
            int status1 = statement.executeUpdate("insert into report (description,location)" + " values('" + description + "','" + location + "')");
            if (status1 > 0) {
                JOptionPane.showMessageDialog(null, "Your report has been submitted successfully.You will receive notification in a few moments", "Successful Submission", JOptionPane.INFORMATION_MESSAGE);
            }
            connection.close();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    @FXML
    public void updateReport(MouseEvent event) throws SQLException, IOException {
        Connection connection1 = DBConnect.getInstance().getConnection();

        String description = description_text.getText();
        String location = location_text.getText();
        String message = "Please check before submission ";

        Statement statement1 = connection1.createStatement();

        try {
            int status2 = statement1.executeUpdate("update report set description = '"+description+"' , location = '"+location+"' where rid = '"+id+"'");
            if (status2 > 0) {
                JOptionPane.showMessageDialog(null, "Report has been updated!", "Report Update", JOptionPane.INFORMATION_MESSAGE);
                int response = JOptionPane.showConfirmDialog(null,"Do you want to exit the app?","Exit Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    Parent root = FXMLLoader.load(getClass().getResource("Report.fxml"));
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.setScene(new Scene(root));
                }
               
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
