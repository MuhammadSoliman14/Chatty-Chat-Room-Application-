import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import Chat.ChatServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SignupController {
    @FXML
    private TextField Email;

    @FXML
    private TextField Password;

    @FXML
    private TextField UserName;

    @FXML
    private Button BtnSignup;

    @FXML
    private Button cancel;

    @FXML
    void EnterEmail(ActionEvent event) {

    }

    @FXML
    void EnterPassword(ActionEvent event) {

    }


    @FXML
    void EnterUserName(ActionEvent event) {

    }
    @FXML
    void cancelButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(((getClass().getClassLoader().getResource("Login.fxml")))); // Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        Stage SignUpstage = new Stage();
        SignUpstage.initStyle(StageStyle.UNDECORATED);
        SignUpstage.setScene(new Scene(root, 500,500));
        SignUpstage.show();
    }


    @FXML
    void Signupp(ActionEvent event) throws Exception {

        String userName = UserName.getText().trim();
        String email = Email.getText().trim();
        String password = Password.getText().trim();

        System.out.println("HERE");
        ChatServer.signup(userName, email, password);
        System.out.println(userName);
        System.out.println(email);
        System.out.println(password);




//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SignUp.fxml"))); // Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
//        Stage SignUpstage = new Stage();
//        SignUpstage.initStyle(StageStyle.UNDECORATED);
//        SignUpstage.setScene(new Scene(root, 500,500));
//        SignUpstage.show();




    }

}
