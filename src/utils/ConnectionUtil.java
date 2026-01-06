package utils;
import java.sql.*;

public class ConnectionUtil {

//    Connection conn = null;
//    public static Connection conDB(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userinfo" , "root", "");
//            return con;
//        } catch (ClassNotFoundException ex) {
//            System.err.println("ConnectionUtil : "+ex.getMessage());
//            return null;
//        } catch (SQLException ex) {
//            System.err.println("ConnectionUtil : "+ex.getMessage());
//            return null;
//        }

        private static String url = "jdbc:mysql://127.0.0.1:3306/userinfo";
        private static String drivername = "com.mysql.jdbc.Driver";
        private static String username = "root";
        private static String password = "";
        public static java.sql.Connection con;
        private static String urlstring;

        public static java.sql.Connection getconnection() throws Exception {

            Class.forName(drivername);
            try {
                con = DriverManager.getConnection(url, username, password);
                System.out.println("connected..");
            } catch (Exception e) {
                System.out.println("something went wrong..." + e);
            }

            return con;
        }


        public static void insertUserData (Connection con, String username, String Email, String password) throws SQLException{
            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into user (userName, password, Email) values (? , ?, ?) ");



            System.out.println("inserting into Database...");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,Email);
            preparedStatement.setString(3,password);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }


        public static boolean loginConfirmation (Connection con, String username, String password ) throws SQLException{
            PreparedStatement preparedStatement  = con.prepareStatement(
                    "select * from user where username = ? and password = ? " );
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,password);
            ResultSet resultset = preparedStatement.executeQuery();
            if (resultset.next()){
                return true ;
            }
            else {
                return false ;
            }
        }

    public static void UpdateUserStatus (Connection connection ,String userName , String status) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update user set status = ? where UserName = ? ");

        preparedStatement.setString(1, status);
        preparedStatement.setString(2, userName);
        preparedStatement.executeUpdate();
    }

    public static ResultSet getUsers (String Status , Connection connection) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select UserName from user Where status = ? "
        );
        preparedStatement.setString(1, Status);
        ResultSet results = preparedStatement.executeQuery();
        return results ;
    }
        }



