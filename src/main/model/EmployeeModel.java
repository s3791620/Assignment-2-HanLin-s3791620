package main.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.SQLConnection;

import java.sql.*;
import java.time.LocalDate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeModel {



    Connection connection = SQLConnection.connect();



    public Boolean addBooking (int user_id, int table_id, String date) throws SQLException {
        PreparedStatement pst = null;
        String query = "insert into booking (user_id, table_id, date, status, active) values (?,?,?,?,?)";

        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, user_id);
            pst.setInt(2, table_id);
            pst.setString(3, date);
            pst.setString(4, "Processing");
            pst.setInt(5, 1);

            pst.addBatch();
            pst.executeBatch();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Boolean ChangeTableStatus (String date, Integer tableID, Integer status) throws SQLException{
        PreparedStatement preparedStatement = null;
        String query = "update tables set table_"+tableID+" = "+status+" where date = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);

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


    public Boolean haveABookingAlready(Integer UserID) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select status from booking where status = ? and user_id = ?";
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1=null;
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Accepted");
            preparedStatement.setInt(2, UserID);
            resultSet = preparedStatement.executeQuery();

            preparedStatement1 = connection.prepareStatement(query);
            preparedStatement1.setString(1, "Processing");
            preparedStatement1.setInt(2, UserID);
            resultSet1 = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                return true;
            }else if (resultSet1.next()){
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


    public Boolean AddTableDate(String date, Integer tableID, Integer status) throws SQLException{
        PreparedStatement pst = null;
        String query = "insert into tables (date, table_"+tableID+") values (?,?)";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, date);
            pst.setInt(2, status);

            pst.addBatch();
            pst.executeBatch();
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            pst.close();
        }
    }


    public Boolean TableAvaiable(String Date, Integer tableID) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select table_"+tableID+" from tables where table_"+tableID+" = ? and date = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, Date);
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


    public Boolean isNotEmpty(String date) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select date from tables where date = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
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


    public Boolean Nobooking(String date, Integer userID) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select date, user_id from booking where date = ? and user_id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, userID);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
            else{
                return true;
            }
        } catch (Exception e)
        {
            return false;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }


    public List<Map<String, Object>> checkSeatStatus(String date) throws SQLException{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select table_1,table_2,table_3,table_4,table_5,table_6,table_7,table_8,table_9,table_10,table_11,table_12,table_13,table_14,table_15,table_16 from tables where date = ?";

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int count = rsmd.getColumnCount();

            while (resultSet.next()){
                Map<String, Object> seats = new HashMap<String, Object>();
                for (int i = 1; i <= count; i ++){
                    seats.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                list.add(seats);
            }
            return list;
        }catch (Exception e){
            return list;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }

    }


    public List<Map<String, Object>> getBooking(Integer employeeID) throws SQLException{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select date, table_id, status from booking where user_id = ?";

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employeeID);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int count = rsmd.getColumnCount();

            while (resultSet.next()){
                Map<String, Object> bookings = new HashMap<String, Object>();
                for (int i = 1; i <= count; i ++){
                    bookings.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                list.add(bookings);
            }
            return list;
        }catch (Exception e){
            return list;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }




    public Boolean cancelBooking(String date, int user_id) throws SQLException{
        PreparedStatement preparedStatement = null;
        String query = "update booking set status = ? where date = ? and user_id = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Canceled");
            preparedStatement.setString(2, date);
            preparedStatement.setInt(3, user_id);

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
