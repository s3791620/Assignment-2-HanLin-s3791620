package main.controller;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.SQLConnection;
import main.model.AdminModel;
import main.model.EmployeeModel;
import main.model.booking;
import main.model.employee;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.lang.model.util.AbstractAnnotationValueVisitor6;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminController implements Initializable{


    public AdminModel adminModel = new AdminModel();
    public EmployeeModel employeeModel = new EmployeeModel();


    //Show users!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML private TableView<employee> tableview;
    @FXML private TableColumn<employee, Integer> firstIDColumn;
    @FXML private TableColumn<employee, String> FirstnameColumn;
    @FXML private TableColumn<employee, String> LastnameColumn;
    @FXML private TableColumn<employee, String> UsernameColumn;
    @FXML private TableColumn<employee, String> PasswordColumn;
    @FXML private TableColumn<employee, Integer> AgeColumn;
    @FXML private TableColumn<employee, Integer> question;
    @FXML private TableColumn<employee, Integer> answer;
    @FXML private TableColumn<employee, Integer> role;


    public void ShowEmployee() throws SQLException{
        tableview.getItems().clear();
        List<Map<String, Object>> list = adminModel.getEmployee("1");

        for (int i=0; i < list.size(); i++){
            tableview.getItems().add(new employee(list.get(i).get("id").toString(), list.get(i).get("name").toString(), list.get(i).get("surname").toString(), list.get(i).get("age").toString(), list.get(i).get("username").toString(), list.get(i).get("password").toString(), list.get(i).get("question").toString(), list.get(i).get("answer").toString(), list.get(i).get("role").toString()));
        }
        firstIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        FirstnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        LastnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        AgeColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        question.setCellValueFactory(new PropertyValueFactory<>("question"));
        answer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
    }


    public void showAdmin() throws SQLException{
        tableview.getItems().clear();
        List<Map<String, Object>> list1 = adminModel.getAdmin("1");

        for (int i=0; i < list1.size(); i++){
            tableview.getItems().add(new employee(list1.get(i).get("id").toString(),list1.get(i).get("name").toString(),list1.get(i).get("surname").toString(),list1.get(i).get("age").toString(),list1.get(i).get("username").toString(),list1.get(i).get("password").toString()));
        }
        firstIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        FirstnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        LastnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    }





    //Table details!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML private DatePicker dateforbook;
    @FXML private Text blockinfor;
    @FXML private Text blockinfor2;
    @FXML private TextField T_ID;
    @FXML private Rectangle table_1,table_2,table_3,table_4,table_5,table_6,table_7,table_8,table_9,table_10,table_11,table_12,table_13,table_14,table_15,table_16;

    public Rectangle[] rectangles;

    public void BlockforLockdown(ActionEvent event) throws SQLException{
        LocalDate bookingdate = dateforbook.getValue();
        String date = bookingdate.getYear() + "-" + String.format("%02d",bookingdate.getMonthValue()) + "-" + String.format("%02d", bookingdate.getDayOfMonth());

        if(adminModel.isEmpty(date)){
            if(adminModel.blockforLockdown(date)){
                blockinfor.setText("Blocked successfully!");
            }
        }else{
            if(adminModel.addBlockDate(date)){
                blockinfor.setText("Blocked successfully!");
            }
        }
    }

    public void Unblock(ActionEvent event) throws SQLException{
        LocalDate bookingdate = dateforbook.getValue();
        String date = bookingdate.getYear() + "-" + String.format("%02d",bookingdate.getMonthValue()) + "-" + String.format("%02d", bookingdate.getDayOfMonth());

        if(adminModel.isEmpty(date)){
            if(adminModel.unblock(date)){
                blockinfor.setText("Unblocked successfully");
            }
        }else{
            blockinfor.setText("There is no block on that day");
        }
    }

    public void BlockAll(ActionEvent event) throws SQLException{
        LocalDate bookingdate = dateforbook.getValue();
        String date = bookingdate.getYear() + "-" + String.format("%02d",bookingdate.getMonthValue()) + "-" + String.format("%02d", bookingdate.getDayOfMonth());

        if(adminModel.isEmpty(date)){
            if(adminModel.BlockAllForLockdown(date)){
                blockinfor.setText("All tables are blocked successfully on " + date + " !");
            }
        }else{
            if(adminModel.BlockAllforNew(date)){
                blockinfor.setText("All tables are blocked successfully on " + date + " !");
            }
        }
    }

    public void BlockATable(ActionEvent event) throws SQLException{
        LocalDate bookingdate = dateforbook.getValue();
        String date = bookingdate.getYear() + "-" + String.format("%02d",bookingdate.getMonthValue()) + "-" + String.format("%02d", bookingdate.getDayOfMonth());

        if(adminModel.isEmpty(date)){
            if(employeeModel.ChangeTableStatus(date, Integer.parseInt(T_ID.getText()), 2)){
                blockinfor2.setText("Table " + T_ID.getText() + " is blocked successfully on " + date + " !");
            }
        }else{
            if(employeeModel.AddTableDate(date, Integer.parseInt(T_ID.getText()), 2)){
                blockinfor2.setText("Table " + T_ID.getText() + " is blocked successfully on " + date + " !");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        rectangles = new Rectangle[]{table_1,table_2,table_3,table_4,table_5,table_6,table_7,table_8,table_9,table_10,table_11,table_12,table_13,table_14,table_15,table_16};

        dateforbook.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

                LocalDate bookingdate = dateforbook.getValue();
                String date2 = bookingdate.getYear() + "-" + String.format("%02d",bookingdate.getMonthValue()) + "-" + String.format("%02d", bookingdate.getDayOfMonth());

                try{
                    checkseat(date2);
                }catch(SQLException throwables){
                    throwables.printStackTrace();
                }
            }
        });
    }

    public void checkseat(String date) throws SQLException{
        List<Map<String, Object>> list = null;
        list = employeeModel.checkSeatStatus(date);

        if(employeeModel.isNotEmpty(date)){
            for (int i = 0; i<16; i ++){
                switch (list.get(0).get(rectangles[i].getId()).toString()){
                    case "0":
                        rectangles[i].setFill(Color.DODGERBLUE);
                        break;
                    case "1":
                        rectangles[i].setFill(Color.RED);
                        break;
                    case "2":
                        rectangles[i].setFill(Color.ORANGE);
                        break;
                    default:
                        break;
                }
            }
        }else{
            for (int i = 0; i<16; i++){
                rectangles[i].setFill(Color.DODGERBLUE);
            }
        }
    }






    //Booking history!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML private TextField accBooking;
    @FXML private TextField reBooking;
    @FXML private TableView<booking> Booking_tableview;
    @FXML private TableColumn<booking, Integer> BookingIDColumn;
    @FXML private TableColumn<booking, String> UserIDColumn;
    @FXML private TableColumn<booking, String> TableIDColumn;
    @FXML private TableColumn<booking, String> DateColum;
    @FXML private TableColumn<booking, String> StatusColumn;

    public void showBooking(ActionEvent event) throws SQLException{
        Booking_tableview.getItems().clear();
        List<Map<String, Object>> list2 = adminModel.getBooking("1");
        for (int i=0; i < list2.size(); i++){
            Booking_tableview.getItems().add(new booking(list2.get(i).get("date").toString(),list2.get(i).get("table_id").toString(),list2.get(i).get("status").toString(),list2.get(i).get("user_id").toString(),list2.get(i).get("booking_id").toString()));
        }
        BookingIDColumn.setCellValueFactory(new PropertyValueFactory<>("booking_id"));
        UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        TableIDColumn.setCellValueFactory(new PropertyValueFactory<>("table_id"));
        DateColum.setCellValueFactory(new PropertyValueFactory<>("date"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }


    public void accept_booking(ActionEvent event) throws SQLException{
        adminModel.accept(Integer.parseInt(accBooking.getText()));
    }


    public void reject_booking(ActionEvent event) throws SQLException{
        adminModel.reject((Integer.parseInt(reBooking.getText())));
        employeeModel.ChangeTableStatus(adminModel.returnDate(Integer.parseInt(reBooking.getText())), adminModel.returnTableID(Integer.parseInt(reBooking.getText())), 0);
    }






    //Edit users!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML private TextField add_firstname;
    @FXML private TextField add_lastname;
    @FXML private TextField add_username;
    @FXML private TextField add_password;
    @FXML private TextField add_age;
    @FXML private TextField add_question;
    @FXML private TextField add_answer;
    @FXML private TextField add_role;
    @FXML private TextField update_id;
    @FXML private Text addTEXT;
    @FXML private Text changeTEXT;
    @FXML private Text deactiveText;


    public void add_employee(ActionEvent event) throws SQLException{
        if(adminModel.add_employee(add_firstname.getText(), add_lastname.getText(), Integer.parseInt(add_age.getText()), add_username.getText(), add_password.getText(), add_question.getText(), add_answer.getText(), add_role.getText())){
            addTEXT.setText("Employee added!");
        }else{
            System.out.println("Something wrong!");
        }
    }


    public void add_admin(ActionEvent event) throws SQLException{
        if(adminModel.add_addmin(add_firstname.getText(), add_lastname.getText(), Integer.parseInt(add_age.getText()), add_username.getText(), add_password.getText())){
            addTEXT.setText("Administrator added!");
        }else{
            addTEXT.setText("Something wrong!");
        }
    }


    public void change_employee(ActionEvent event) throws SQLException{
        if(adminModel.change_employee(Integer.parseInt(update_id.getText()), add_firstname.getText(), add_lastname.getText(), Integer.parseInt(add_age.getText()), add_username.getText(), add_password.getText(), add_question.getText(), add_answer.getText(), add_role.getText())){
            changeTEXT.setText("Employee changed successfully!");
        }else{
            changeTEXT.setText("Something wrong!");
        }
    }

    public void change_admin(ActionEvent event) throws SQLException{
        if(adminModel.change_admin(add_firstname.getText(), add_lastname.getText(), Integer.parseInt(add_age.getText()), add_username.getText(), add_password.getText(), Integer.parseInt(update_id.getText()))) {
            addTEXT.setText("Administrator changed successfully!");
        }else{
            addTEXT.setText("Something wrong!");
        }
    }

    public void deactiveEmployee(ActionEvent event) throws SQLException{
        if(adminModel.deEmployee(Integer.parseInt(update_id.getText()))) {
            deactiveText.setText("Employee " + update_id.getText() + " deactivated!");
        }else{
            deactiveText.setText("Something wrong!");
        }
    }

    public void deactiveAdmin(ActionEvent event) throws SQLException{
        if(adminModel.deAdmin(Integer.parseInt(update_id.getText()))) {
            deactiveText.setText("Admin " + update_id.getText() + " deactivated!");
        }else{
            deactiveText.setText("Something wrong!");
        }
    }






    //back to login !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML private Button BackToLogin;


    @FXML public void BackToLogin(ActionEvent event) {
        Scene scene = BackToLogin.getScene();
        Window window = scene.getWindow();
        Stage primaryStage = (Stage) window;

        try{
            Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
            primaryStage.setScene(new Scene(root, 544,369));

        } catch (IOException e){
            System.out.println("Cannot load the second scene");
        }
    }


}
