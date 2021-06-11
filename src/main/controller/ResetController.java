package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.model.ResetModel;

import javax.swing.*;
import java.io.IOException;

public class ResetController {
    ResetModel resetModel = new ResetModel();

    @FXML private Button BackToLogin;
    @FXML private Button confirmName;
    @FXML private Button confirmAnswer;
    @FXML private Button confirmNew;
    @FXML private TextField username;
    @FXML private TextField answer;
    @FXML private TextField newpassword;
    @FXML private Text Question;
    @FXML private Text NewPassword;
    @FXML private Text Answer;


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

    public void confirmName(ActionEvent event){
        try{
            Question.setText(resetModel.nameCheck(username.getText()));
        }catch(Exception e){
        }
    }

    public void confirmAnswer(ActionEvent event){
        try{
            if (resetModel.confirmAns(answer.getText(),username.getText())){
                Answer.setText("Answer Correct!");
            }else{
                Answer.setText("Answer not correct!");
            }
        }catch (Exception e){
        }
    }

    public void confirmNew(ActionEvent event){
        try{
            if(resetModel.confirmNew(username.getText(), newpassword.getText())){
                NewPassword.setText(newpassword.getText());
            }else{
                NewPassword.setText("Please make sure your username is correct!");
            }
        }catch(Exception e){
        }

    }
}

