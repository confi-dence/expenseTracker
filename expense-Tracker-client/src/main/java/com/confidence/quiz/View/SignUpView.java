package com.confidence.quiz.View;

import com.confidence.quiz.controllers.LoginControllers;
import com.confidence.quiz.controllers.SignUpController;
import com.confidence.quiz.utilis.Utilitie;
import com.confidence.quiz.utilis.ViewNavigator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class SignUpView {

    private Label expenseTrackerLabel = new Label( "Expense Tracker");
    private TextField nameField = new TextField();



    private TextField usernameField = new TextField();
    private PasswordField passwordField = new PasswordField();


    private PasswordField rePasswordField = new PasswordField();

    private Button registerButton = new Button("Register");
    private Label loginLabel = new Label(" Already have an account? Login here");

    public void show(){
        new SignUpController(this);
        Scene scene = createScene();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        ViewNavigator.switchViews(scene);

    }

    private Scene createScene() {
        VBox mainContainer = new VBox(44);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        VBox createsignupform = createSignUpform();
        mainContainer.getStyleClass().addAll("main-background");
        expenseTrackerLabel.getStyleClass().addAll("header", "text-white");
        mainContainer.getChildren().addAll(expenseTrackerLabel,createsignupform);
return new Scene(mainContainer, Utilitie.App_Width, Utilitie.App_Height);
    }

    private VBox createSignUpform(){
        VBox signUpForm = new VBox(30);
        signUpForm.setAlignment(Pos.CENTER);

        nameField.getStyleClass().addAll("field-background", "text-light-gray", "text-size-lg", "rounded-border");
        nameField.setPromptText("Enter Name");
        nameField.setMaxWidth(473);

        usernameField.getStyleClass().addAll("field-background", "text-light-gray", "text-size-lg", "rounded-border");
        usernameField.setPromptText("Enter Email");
        usernameField.setMaxWidth(473);

        passwordField.getStyleClass().addAll("field-background", "text-light-gray", "text-size-lg", "rounded-border");
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(473);

        rePasswordField.getStyleClass().addAll("field-background", "text-light-gray", "text-size-lg", "rounded-border");
        rePasswordField.setPromptText("Re-Enter Password");
        rePasswordField.setMaxWidth(473);

        registerButton.getStyleClass().addAll("text-size-lg", "bg-light-blue", "text-white", "text-weight-700", "rounded-border");
        registerButton.setMaxWidth(473);

        loginLabel.getStyleClass().addAll("text-size-md", "text-light-gray", "text-underline", "link-text");

        signUpForm.getChildren().addAll(nameField, usernameField, passwordField, rePasswordField, registerButton, loginLabel);
        return signUpForm;
    }

    public Label getExpenseTrackerLabel() {
        return expenseTrackerLabel;
    }

    public void setExpenseTrackerLabel(Label expenseTrackerLabel) {
        this.expenseTrackerLabel = expenseTrackerLabel;
    }

    public TextField getNameFeild() {
        return nameField;
    }

    public void setNameFeild(TextField nameFeild) {
        nameField = nameFeild;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public PasswordField getRePasswordField() {
        return rePasswordField;
    }

    public void setRePasswordField(PasswordField rePasswordField) {
        this.rePasswordField = rePasswordField;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(Button registerButton) {
        this.registerButton = registerButton;
    }

    public Label getLoginLabel() {
        return loginLabel;
    }

    public void setLoginLabel(Label loginLabel) {
        this.loginLabel = loginLabel;
    }


}
