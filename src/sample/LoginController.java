package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements Initializable{

    @FXML
    public Button lbl_close;
    @FXML
    public Button lbl_min;
    @FXML
    public Button lbl_max;
    @FXML
    private Button create;
    @FXML
    private Button signin;
    @FXML
    public TextField textfield_create;
    @FXML
    private TextField textfield_signin;
    @FXML
    public PasswordField password_create;
    @FXML
    private PasswordField password_signin;
    @FXML
    Button previous_google;
    @FXML
    private CheckBox showpassword1;
    @FXML
    private CheckBox showpassword2;
    @FXML
    private TextField password_text1;
    @FXML
    private TextField password_text2;

    Connection connection = DBConnect.getInstance().getConnection();

    @FXML
    public void previousGoogleAction(MouseEvent event) throws IOException {
        if(event.getSource() == previous_google) {
            Parent root = FXMLLoader.load(getClass().getResource("User Authentication.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }



    @FXML
    public void closeAction(MouseEvent event) {
        if(event.getSource() == lbl_close) {
            System.exit(0);
        }
    }
    @FXML
    public void minAction(MouseEvent event) {
        if(event.getSource() == lbl_min) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setIconified(true);
        }
    }

    @FXML
    public void maxAction(MouseEvent event) {
        if(event.getSource() == lbl_max) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setFullScreenExitHint(" ");
            stage.setFullScreen(true);
        }
    }

    @FXML
    public void togglevisiblePassword(MouseEvent event) {
        if(showpassword1.isSelected()) {
            password_text1.setText(password_create.getText());
            password_text1.setVisible(false);
            password_create.setVisible(true);
            return;
        }
        password_create.setText(password_text1.getText());
        password_create.setVisible(false);
        password_text1.setVisible(true);
    }

    @FXML
    public void togglevisiblePassword1(MouseEvent event) {
        if(showpassword2.isSelected()) {
            password_text2.setText(password_signin.getText());
            password_text2.setVisible(false);
            password_signin.setVisible(true);
            return;
        }
        password_signin.setText(password_text2.getText());
        password_signin.setVisible(false);
        password_text2.setVisible(true);
    }


    @FXML
    public void createAcc(MouseEvent event) throws SQLException, IOException {

            String username = textfield_create.getText();
            String password = password_create.getText();
            String password_hidden = password_text1.getText();
            password = password_hidden;
            Statement statement = connection.createStatement();

        try {
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please create again!", "Create Account Error", JOptionPane.ERROR_MESSAGE);
            } else {

                int status = statement.executeUpdate("insert into users (username,password)" + " values('" + username + "','" + password + "')");

                if (status > 0) {
                    JOptionPane.showMessageDialog(null, "Account created successfully!", "Create Account", JOptionPane.INFORMATION_MESSAGE);
                }
                connection.close();
            }
            } catch(SQLException e){
                e.printStackTrace();
            } finally{
                connection.close();
            }
    }

    @FXML
    public void login(MouseEvent event) throws SQLException, IOException {

        String username, password, password_hidden1;

        username = textfield_signin.getText();
        password = password_signin.getText();
        password_hidden1 = password_text2.getText();
        password = password_hidden1;

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from users where username" +
                " = '" + username + "' and password = '" + password + "'");

        if (resultSet.next()) {
            JOptionPane.showMessageDialog(null,"Sign in successfull!","Sign in Success",JOptionPane.INFORMATION_MESSAGE);
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        } else {
            JOptionPane.showMessageDialog(null,"Please try again!","Login Account",JOptionPane.ERROR_MESSAGE);
        }
        connection.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.togglevisiblePassword(null);
        this.togglevisiblePassword1(null);
    }
}
