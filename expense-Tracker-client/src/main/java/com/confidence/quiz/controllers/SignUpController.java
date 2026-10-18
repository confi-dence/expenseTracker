package com.confidence.quiz.controllers;

import com.confidence.quiz.View.LoginView;
import com.confidence.quiz.View.SignUpView;
import com.confidence.quiz.utilis.SqlUtil;
import com.confidence.quiz.utilis.Utilitie;
import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class SignUpController {
    private SignUpView signUpView;


    public SignUpController(SignUpView signUpView) {
        this.signUpView = signUpView;
        initialize();
    }
    private void initialize(){
        signUpView.getLoginLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new LoginView().show();
            }
        });


        signUpView.getRegisterButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!validateInput()){
                    Utilitie.showAlertDialog(Alert.AlertType.ERROR, "Invalide Input");
                    return;
                }
//                extract the data in the fields

                String name = signUpView.getNameFeild().getText();
                String username = signUpView.getUsernameField().getText();
                String password = signUpView.getPasswordField().getText();
//cretaed the jsondata to send to our post request
                JsonObject jsonData = new JsonObject();
                jsonData.addProperty("name", name);
                jsonData.addProperty("email", username);
                jsonData.addProperty("password", password);

//                send in our Post request to create the user

                boolean postCretaAccountStatus = SqlUtil.postCreateUser(jsonData);

//                depending on the result we will display the corresponding alert message
                if (postCretaAccountStatus){
                    Utilitie.showAlertDialog(Alert.AlertType.INFORMATION, "Successfully created new Account");
                    new LoginView().show();
                }else {
                    Utilitie.showAlertDialog(Alert.AlertType.ERROR, "Failed to create new account");

                }

            }
        });
    }

    private boolean validateInput(){
        if(signUpView.getNameFeild().getText().isEmpty()) return false;
        if(signUpView.getUsernameField().getText().isEmpty()) return false;
        if(signUpView.getNameFeild().getText().isEmpty()) return false;
        if(signUpView.getNameFeild().getText().isEmpty()) return false;
        if (!signUpView.getPasswordField().getText().equals(signUpView.getRePasswordField().getText())) return false;
        // entered email doesn't already exist in our database

        if(SqlUtil.getUserByEmail(signUpView.getUsernameField().getText()) != null ) return false;

        return true;
    }
}
