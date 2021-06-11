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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.model.EmployeeModel;
import main.model.booking;
import main.model.employee;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    public EmployeeModel employeeModel = new EmployeeModel();

    @FXML private Button BackToLogin;
    @FXML private DatePicker date;
    @FXML private DatePicker canceldate;
    @FXML private TextField table_id;
    @FXML private TextField cancelTableID;
    @FXML private Text cancelInfor;
    @FXML private Text bookinfor;
    @FXML private TableView<booking> bookingview;
    @FXML private TableColumn<booking, String> date_view;
    @FXML private TableColumn<booking, String> tableID_view;
    @FXML private TableColumn<booking, String> status_view;
    @FXML private Rectangle table_1,table_2,table_3,table_4,table_5,table_6,table_7,table_8,table_9,table_10,table_11,table_12,table_13,table_14,table_15,table_16;

    public Rectangle[] rectangles;



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


    public void add_booking(ActionEvent event) throws Exception{

        LocalDate bookingdate = date.getValue();
        String date1 = bookingdate.getYear() + "-" + String.format("%02d",bookingdate.getMonthValue()) + "-" + String.format("%02d", bookingdate.getDayOfMonth());

        if (!employeeModel.haveABookingAlready(LoginController.uid)){
            if (employeeModel.isNotEmpty(date1) && employeeModel.Nobooking(date1, LoginController.uid)){
                if(employeeModel.TableAvaiable(date1, Integer.parseInt(table_id.getText()))) {
                    if (employeeModel.addBooking(LoginController.uid, Integer.parseInt(table_id.getText()), date1) && employeeModel.ChangeTableStatus(date1, Integer.parseInt(table_id.getText()), 1)) {
                        bookinfor.setText("Booked successfully");
                    } else {
                        bookinfor.setText("There are something wrong!");
                    }
                } else{
                    bookinfor.setText("Table is not avaiable!");
                }
            } else if (!employeeModel.isNotEmpty(date1) && employeeModel.Nobooking(date1, LoginController.uid)){
                if (employeeModel.addBooking(LoginController.uid, Integer.parseInt(table_id.getText()), date1) && employeeModel.AddTableDate(date1,Integer.parseInt(table_id.getText()), 1)) {
                    bookinfor.setText("Booked successfully!");
                }else{
                    bookinfor.setText("There are something wrong");
                }
            }
            else if(!employeeModel.Nobooking(date1, LoginController.uid)){
                bookinfor.setText("You already have a booking on this day!");
            }
        }else{
            bookinfor.setText("You have a booking already!");
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        rectangles = new Rectangle[]{table_1,table_2,table_3,table_4,table_5,table_6,table_7,table_8,table_9,table_10,table_11,table_12,table_13,table_14,table_15,table_16};

        date.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

                LocalDate bookingdate = date.getValue();
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



    public void showBookings(ActionEvent event) throws Exception{

        bookingview.getItems().clear();
        List<Map<String, Object>> list = employeeModel.getBooking(LoginController.uid);

        System.out.println(list);

        for (int i=0; i < list.size(); i++){
            bookingview.getItems().add(new booking(list.get(i).get("date").toString(), list.get(i).get("table_id").toString(), list.get(i).get("status").toString()));
        }

        date_view.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableID_view.setCellValueFactory(new PropertyValueFactory<>("table_id"));
        status_view.setCellValueFactory(new PropertyValueFactory<>("status"));

    }





    public void cancelBooking(ActionEvent event) throws Exception{

        LocalDate bookingdate = canceldate.getValue();
        String date2 = bookingdate.getYear() + "-" + String.format("%02d",bookingdate.getMonthValue()) + "-" + String.format("%02d", bookingdate.getDayOfMonth());

        try{
            if(employeeModel.cancelBooking(date2, LoginController.uid) && employeeModel.ChangeTableStatus(date2, Integer.parseInt(cancelTableID.getText()), 0)){
                cancelInfor.setText("Booking canceled!");
            }else{
                cancelInfor.setText("No that day booking!");
            }
        }catch (Exception e){
        }
    }




}
