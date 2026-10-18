package com.confidence.quiz;

import com.confidence.quiz.View.DashboardView;
import com.confidence.quiz.View.LoginView;
import com.confidence.quiz.View.SignUpView;
import com.confidence.quiz.utilis.ViewNavigator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Expense Tracker");
        ViewNavigator.setMainStage(stage);
        new DashboardView("hello@email.com").show();
//        new SignUpView().show();
    }
}
