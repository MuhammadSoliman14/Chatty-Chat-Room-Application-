package Chat;



import java.io.IOException;
        import java.net.*;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
import utils.ConnectionUtil;

import static utils.ConnectionUtil.insertUserData;

public class ChatServer {
    private  ServerSocket serverSocket  ;

    public ChatServer ( ServerSocket serverSocket  ){
        this.serverSocket = serverSocket ;
    }

    public void StartServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                ChatHandler chatHandler = new ChatHandler(socket);
                Thread thread = new Thread(chatHandler);
                thread.start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean login(String UserName , String password) throws Exception {
        Connection con = ConnectionUtil.getconnection();
        return ConnectionUtil.loginConfirmation(con, UserName, password);
    }

    public static void signup(String UserName ,String Email , String password ) throws Exception {
        Connection con = ConnectionUtil.getconnection();
        insertUserData(con, UserName,Email,password);
    }

    public static void status( String UserName ,String status) throws Exception {
        Connection connection = ConnectionUtil.getconnection();
        ConnectionUtil.UpdateUserStatus(connection, UserName, status);
    }

    public static ResultSet Users (String status) throws Exception {
        Connection connection = ConnectionUtil.getconnection();
        ResultSet results = ConnectionUtil.getUsers(status, connection);
        return results ;
    }

    public static void main (String [] args) throws IOException{

        ServerSocket serverSocket = new ServerSocket(3306);
        ChatServer Server = new ChatServer(serverSocket);
        Server.StartServer();

    }


}







