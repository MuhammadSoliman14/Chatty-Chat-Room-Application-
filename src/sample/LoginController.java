package sample;

import Client.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private TextField usertxt;

    @FXML
    private TextField pass;

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginBtn;

    @FXML
    private Button SignupBtn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public LoginController() throws Exception {
        con = ConnectionUtil.con;
    }


    // -----
    ConnectionUtil connect = new ConnectionUtil();
    Connection con = connect.getconnection();
    PreparedStatement preparedStatement;
    ResultSet resultset;

    @FXML
    private void login() throws IOException {
        String status = "Success";
        String username = usertxt.getText();
        String password = pass.getText();
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("ERRRRRRRRRROR!!!!!!!!!!");
        } else {
            //query
            String sql = "SELECT * FROM user Where username = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("Enter correct email and password");
                } else {
                    System.out.println("Login Successful..Redirecting..");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }


        Stage Chatinterface = (Stage) loginBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Client/clientInterface.fxml"));
        Parent UserInterface = loader.load();
        ClientController client = (ClientController) loader.getController();
        client.UserName = usertxt.getText();
        Scene scene = new Scene(UserInterface);
        Chatinterface.setScene(scene);
        Chatinterface.show();

    }
    public void signupScene(ActionEvent event) throws IOException {



        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SignUp.fxml"))); // Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        Stage SignUpstage = new Stage();
        SignUpstage.initStyle(StageStyle.UNDECORATED);
        SignUpstage.setScene(new Scene(root, 500,500));
        SignUpstage.show();


    }
}
