package com.confidence.quiz.View;


import com.confidence.quiz.controllers.LoginControllers;
import com.confidence.quiz.utilis.Utilitie;
import com.confidence.quiz.utilis.ViewNavigator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Objects;


public class LoginView {


    private Label expenseTrackerLabel = new Label( "Expense Tracker");
    private TextField usernameFeild = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Button loginButton = new Button("Login");
    private Label signupLabel = new Label(" Don't have an account? Click Here");

    public void show(){
        Scene scene = createScene();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        ViewNavigator.switchViews(scene);
        new LoginControllers(this);
    }

    private Scene createScene() {
        VBox mainContainer = new VBox(74);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        VBox loginFormBox = createLoginFormBox();
        mainContainer.getChildren().addAll(expenseTrackerLabel, loginFormBox);
        expenseTrackerLabel.getStyleClass().addAll("header", "text-white");
        mainContainer.getStyleClass().addAll("main-background");
        return new Scene(mainContainer, Utilitie.App_Width, Utilitie.App_Height);

    }

    private VBox createLoginFormBox(){
        VBox loginFormVBox = new VBox(51);
        loginFormVBox.setAlignment(Pos.CENTER);
        loginFormVBox.getChildren().addAll(usernameFeild, passwordField,loginButton,signupLabel);
        usernameFeild.getStyleClass().addAll("field-background","text-light-gray","text-size-lg", "rounded-border");
        usernameFeild.setMaxWidth(473);
        usernameFeild.setPromptText("Enter Username");

        loginButton.getStyleClass().addAll("text-size-lg","bg-light-blue","text-white","text-weight-700","rounded-border");
        loginButton.setMaxWidth(473);

        passwordField.getStyleClass().addAll("field-background","text-light-gray","text-size-lg","rounded-border");
        passwordField.setMaxWidth(473);
        passwordField.setPromptText("Enter Password");


        signupLabel.getStyleClass().addAll("text-size-md","text-light-gray","text-underline","link-text");


        return loginFormVBox;
    }


    public Label getExpenseTrackerLabel() {
        return expenseTrackerLabel;
    }

    public void setExpenseTrackerLabel(Label expenseTrackerLabel) {
        this.expenseTrackerLabel = expenseTrackerLabel;
    }

    public TextField getUsernameFeild() {
        return usernameFeild;
    }

    public void setUsernameFeild(TextField usernameFeild) {
        this.usernameFeild = usernameFeild;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    public Label getSignupLabel() {
        return signupLabel;
    }

    public void setSignupLabel(Label signupLabel) {
        this.signupLabel = signupLabel;
    }

}
