package main.model;

import javafx.scene.control.TextField;
import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetModel {

    Connection connection = SQLConnection.connect();

    public String nameCheck(String name) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select question from employee where username = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String question = resultSet.getString("question");
                return question;
            }
        }catch (Exception e) {
            return null;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return null;
    }


    public Boolean confirmAns(String answer, String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select answer and username from employee where answer = ? and username = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, answer);
            preparedStatement.setString(2, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e)
        {
            return false;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public Boolean confirmNew(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "update employee set password = ? where username = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, username);
            preparedStatement.setString(1, password);

            preparedStatement.execute();
            preparedStatement.executeUpdate();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }finally {
            preparedStatement.close();
        }
    }

}
