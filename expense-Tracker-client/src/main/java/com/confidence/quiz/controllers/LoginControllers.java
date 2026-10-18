package com.confidence.quiz.controllers;

import com.confidence.quiz.View.DashboardView;
import com.confidence.quiz.View.LoginView;
import com.confidence.quiz.View.SignUpView;
import com.confidence.quiz.utilis.ApiUtil;
import com.confidence.quiz.utilis.SqlUtil;
import com.confidence.quiz.utilis.Utilitie;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.HttpURLConnection;

public class LoginControllers {

    private LoginView loginView;

    public LoginControllers(LoginView loginview){
        this.loginView = loginview;
        initialize();
    }

    public void initialize(){
        loginView.getLoginButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                System.out.println("click");
                if(!validateUser()) return;
                String email = loginView.getUsernameFeild().getText();
                String password = loginView.getPasswordField().getText();
                if(SqlUtil.postLoginUser(email, password)){
                    Utilitie.showAlertDialog(Alert.AlertType.INFORMATION, "Login Successful!");
                    new DashboardView(email).show();
                }else{
                    Utilitie.showAlertDialog(Alert.AlertType.ERROR, "Failed to authenticate");
                }
            }

        });

        loginView.getSignupLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new SignUpView().show();
            }
        });
    }

    public boolean validateUser(){
//        empty username
        if (loginView.getUsernameFeild().getText().isEmpty()){
            return false;
        }
//        emoty passowrd
        if(loginView.getPasswordField().getText().isEmpty()){
            return false;
        }
        return true;
    }
}
