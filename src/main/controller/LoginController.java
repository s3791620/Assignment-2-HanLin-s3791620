package main.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.model.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel();
    @FXML private Label isConnected;
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private Button changeRegister;
    @FXML private Button changeReset;
    @FXML private ChoiceBox loginmod;


    public static Integer uid;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (loginModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

        loginmod.setItems(FXCollections.observableArrayList("Admin","Employee"));

    }


    public void Login(ActionEvent event){

        try {
            if (loginModel.AdminLogin(txtUsername.getText(),txtPassword.getText())){
                Scene scene = changeRegister.getScene();
                Window window = scene.getWindow();
                Stage primaryStage = (Stage) window;

                try{
                    Parent root = FXMLLoader.load(getClass().getResource("../ui/admin.fxml"));
                    primaryStage.setScene(new Scene(root, 726,526));
                } catch (IOException e){
                    System.out.println("Cannot load the second scene");
                }

            }
            else if (loginModel.isLogin(txtUsername.getText(),txtPassword.getText())!=null){
                uid = loginModel.isLogin(txtUsername.getText(),txtPassword.getText());
                Scene scene = changeRegister.getScene();
                Window window = scene.getWindow();
                Stage primaryStage = (Stage) window;

                try{
                    Parent root = FXMLLoader.load(getClass().getResource("../ui/employee.fxml"));
                    primaryStage.setScene(new Scene(root, 726,526));
                } catch (IOException e){
                    System.out.println("Cannot load the second scene");
                }
            } else{
                isConnected.setText("username and password is incorrect");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML public void changeToRegister(ActionEvent event) {

        Scene scene = changeRegister.getScene();
        Window window = scene.getWindow();
        Stage primaryStage = (Stage) window;

        try{
            Parent root = FXMLLoader.load(getClass().getResource("../ui/register.fxml"));
            primaryStage.setScene(new Scene(root, 600,400));
        } catch (IOException e){
            System.out.println("Cannot load the second scene");
        }
    }

    @FXML public void changeToReset(ActionEvent event) {

        Scene scene = changeReset.getScene();
        Window window = scene.getWindow();
        Stage primaryStage = (Stage) window;

        try{
            Parent root = FXMLLoader.load(getClass().getResource("../ui/resetpassword.fxml"));
            primaryStage.setScene(new Scene(root, 600,400));
        } catch (IOException e){
            System.out.println("Cannot load the second scene");
        }
    }





}
