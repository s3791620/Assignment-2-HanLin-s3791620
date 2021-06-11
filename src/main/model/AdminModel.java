package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.SQLConnection;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminModel {


    Connection connection = SQLConnection.connect();




    public List<Map<String, Object>> getEmployee(String active) throws SQLException{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select id,name,surname,age,username,password,question,answer,role from employee where active = ?";

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, active);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int count = rsmd.getColumnCount();

            while (resultSet.next()){
                Map<String, Object> employees = new HashMap<String, Object>();
                for (int i = 1; i <= count; i ++){
                    employees.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                list.add(employees);
            }
            return list;
        }catch (Exception e){
            return list;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }


    public List<Map<String, Object>> getAdmin(String active) throws SQLException{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select id,name,surname,age,username,password from admin where active = ?";

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, active);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int count = rsmd.getColumnCount();

            while (resultSet.next()){
                Map<String, Object> employees = new HashMap<String, Object>();
                for (int i = 1; i <= count; i ++){
                    employees.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                list.add(employees);
            }
            return list;
        }catch (Exception e){
            return list;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }


    public List<Map<String, Object>> getBooking(String active) throws SQLException{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select booking_id,user_id,table_id,date,status from booking where active = ?";

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, active);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int count = rsmd.getColumnCount();

            while (resultSet.next()){
                Map<String, Object> booking = new HashMap<String, Object>();
                for (int i = 1; i <= count; i ++){
                    booking.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                list.add(booking);
            }
            return list;
        }catch (Exception e){
            return list;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }


    public String accept(int bookingID) throws SQLException{
        PreparedStatement preparedStatement = null;
        String query = "update booking set status = ? where booking_id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Accepted");
            preparedStatement.setInt(2, bookingID);

            preparedStatement.execute();
            preparedStatement.executeUpdate();
            return null;
        }
        catch (Exception e)
        {
            return "false";
        }finally {
            preparedStatement.close();
        }
    }




    public String reject(int bookingID) throws SQLException{
        PreparedStatement preparedStatement = null;
        String query = "update booking set status = ? where booking_id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Rejected");
            preparedStatement.setInt(2, bookingID);

            preparedStatement.execute();
            preparedStatement.executeUpdate();
            return null;
        }
        catch (Exception e)
        {
            return "false";
        }finally {
            preparedStatement.close();
        }
    }


    public Boolean add_employee(String name, String surname, Integer age, String username, String password, String question, String answer, String role) throws SQLException{
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
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            pst.close();
        }
    }






    public Boolean add_addmin(String name, String surname, Integer age, String username, String password) throws SQLException{
        PreparedStatement pst = null;
        String query = "insert into admin (name, surname, age, username, password, active) values (?,?,?,?,?,?)";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, surname);
            pst.setInt(3, age);
            pst.setString(4, username);
            pst.setString(5, password);
            pst.setInt(6,1);


            pst.addBatch();
            pst.executeBatch();
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            pst.close();
        }
    }

    public Boolean change_employee(int em_id, String name, String surname, int age, String username, String password, String question, String answer, String role) throws SQLException{
        PreparedStatement preparedStatement = null;
        String query = "update employee set name = ?, surname = ?, age = ?, username = ?, password = ?, question = ?, answer = ?, role = ? where id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, question);
            preparedStatement.setString(7, answer);
            preparedStatement.setString(8, role);
            preparedStatement.setInt(9, em_id);

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


    public Boolean change_admin(String name, String surname, Integer age, String username, String password, int id) throws SQLException{
        PreparedStatement pst = null;
        String query = "update admin set name = ?, surname = ?, age = ?, username = ?, password = ? where id = ?";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, surname);
            pst.setInt(3, age);
            pst.setString(4, username);
            pst.setString(5, password);
            pst.setInt(6, id);


            pst.addBatch();
            pst.executeBatch();
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            pst.close();
        }
    }

    public Boolean deEmployee(Integer id) throws SQLException{
        PreparedStatement pst = null;
        String query = "update employee set active = ? where id = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, 2);
            pst.setInt(2, id);

            pst.addBatch();
            pst.executeBatch();
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            pst.close();
        }
    }


    public Boolean deAdmin(Integer id) throws SQLException{
        PreparedStatement pst = null;
        String query = "update admin set active = ? where id = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, 2);
            pst.setInt(2, id);

            pst.addBatch();
            pst.executeBatch();
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            pst.close();
        }
    }


    public Boolean isEmpty(String date) throws SQLException{
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

    public Boolean blockforLockdown(String date) throws SQLException{
        PreparedStatement pst = null;
        String query = "update tables set table_1 = 2, table_3 = 2, table_5 = 2, table_7 = 2, table_9 = 2, table_11 = 2, table_13 = 2, table_15 = 2 where date = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, date);

            pst.addBatch();
            pst.executeBatch();
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            pst.close();
        }
    }

    public Boolean BlockAllForLockdown(String Date) throws SQLException{
        PreparedStatement pst = null;
        String query = "update tables set table_1 = 2, table_2 = 2, table_3 = 2, table_4 = 2, table_5 = 2, table_6 = 2, table_7 = 2, table_8 = 2, table_9 = 2, table_10 = 2, table_11 = 2, table_12 = 2, table_13 = 2, table_14 = 2, table_15 = 2, table_16 = 2 where date = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, Date);

            pst.addBatch();
            pst.executeBatch();
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            pst.close();
        }
    }

    public Boolean unblock(String date) throws SQLException{
        PreparedStatement pst = null;
        String query = "update tables set table_1 = 0, table_3 = 0, table_5 = 0, table_7 = 0, table_9 = 0, table_11 = 0, table_13 = 0, table_15 = 0 where date = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, date);

            pst.addBatch();
            pst.executeBatch();
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            pst.close();
        }
    }


    public Boolean BlockAllforNew(String Date) throws SQLException{
        PreparedStatement pst = null;
        String query = "insert into tables (date, table_1, table_2, table_3, table_4, table_5, table_6, table_7, table_8, table_9, table_10, table_11, table_12, table_13, table_14, table_15, table_16) values (?,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2)";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, Date);

            pst.addBatch();
            pst.executeBatch();
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            pst.close();
        }
    }


    public Boolean addBlockDate(String date) throws SQLException{
        PreparedStatement pst = null;
        String query = "insert into tables (date, table_1, table_2, table_3, table_4, table_5, table_6, table_7, table_8, table_9, table_10, table_11, table_12, table_13, table_14, table_15, table_16) values (?,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0)";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, date);

            pst.addBatch();
            pst.executeBatch();
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            pst.close();
        }
    }

    public Integer returnTableID(Integer BookingID) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select table_id from booking where booking_id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, BookingID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("table_id");
                return id;
            }
        }catch (Exception e) {
            return null;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return null;
    }

    public String returnDate(Integer BookingID) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select date from booking where booking_id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, BookingID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                return date;
            }
        }catch (Exception e) {
            return null;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return null;
    }


}
