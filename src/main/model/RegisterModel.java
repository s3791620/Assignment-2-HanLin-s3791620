package main.model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterModel {

    Connection connection = SQLConnection.connect();

    public String register(String name, String surname, Integer age, String username, String password, String question, String answer, String role) throws SQLException {

        PreparedStatement pst = null;
        String query = "insert into employee (name, surname, age, username, question, password, answer, role, active) values (?,?,?,?,?,?,?,?,?)";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, surname);
            pst.setInt(3, age);
            pst.setString(4, username);
            pst.setString(5, question);
            pst.setString(6, password);
            pst.setString(7, answer);
            pst.setString(8, role);
            pst.setInt(9, 1);

            pst.addBatch();
            pst.executeBatch();
            return "";
        }catch (Exception e) {
            return "false";
        }finally {
            pst.close();
        }

    }
}
