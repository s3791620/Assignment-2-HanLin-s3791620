package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.SQLConnection;
import main.model.RegisterModel;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class RegisterController{

    public RegisterModel registermodel = new RegisterModel();

    @FXML private Button ChangeToLogin;
    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField age;
    @FXML private TextField question;
    @FXML private TextField answer;
    @FXML private TextField role;
    @FXML private Button confirm;


    @FXML public void ChangeToLogin(ActionEvent event) {

        Scene scene = ChangeToLogin.getScene();
        Window window = scene.getWindow();
        Stage primaryStage = (Stage) window;

        try{
            Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
            primaryStage.setScene(new Scene(root, 544,369));
        } catch (IOException e){
            System.out.println("Cannot load the second scene");
        }
    }

    public void add_user(ActionEvent event) throws Exception {

        registermodel.register(name.getText(),surname.getText(),Integer.parseInt(age.getText()),username.getText(),password.getText(),question.getText(),answer.getText(),role.getText());


    }



}
