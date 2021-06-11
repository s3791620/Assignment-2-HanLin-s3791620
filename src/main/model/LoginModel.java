package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    Connection connection;

    public LoginModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public Boolean isDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch(Exception e){
            return false;
        }
    }

    public Integer isLogin(String user, String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select id from employee where username = ? and password = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                return id;
            }
            else{
                return null;
            }
        }
        catch (Exception e)
        {
            return null;
        }finally {
           preparedStatement.close();
           resultSet.close();
        }

    }


    public Boolean AdminLogin(String user, String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from admin where username = ? and password= ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    //Learnt from YouTube link: youtube.com/watch?v=tw_NXq08NUE
   /* public static ObservableList<employee> getDatausers() {
        Connection connection = null;
        ObservableList<employee> listM = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = connection.prepareStatement("select * from employee");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                listM.add(new employee(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("surname"), rs.getInt("age"), rs.getString("username"),rs.getString("password")));
            }

        }catch (Exception e){
        }
        return listM;
    }*/


}
