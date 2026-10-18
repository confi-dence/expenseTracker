package com.confidence.quiz.controllers;

import com.confidence.quiz.View.DashboardView;

import com.confidence.quiz.dialog.CreateNewCAtegoryDialog;
import com.confidence.quiz.dialog.VieworEditTransactionCategoryDialog;
import com.confidence.quiz.models.User;
import com.confidence.quiz.utilis.SqlUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DashboardController {

    private User user;

    private DashboardView dashboardView;

    public DashboardController(DashboardView dashboardView){
        this.dashboardView = dashboardView;
        fetchUSerData();
        initialize();
    }

    private void fetchUSerData() {
        user = SqlUtil.getUserByEmail(dashboardView.getEmail());
    }

    private void initialize(){
        addMenuActions();
    }

    private void addMenuActions() {
        dashboardView.getCreateCategoryMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new CreateNewCAtegoryDialog(user).showAndWait();
            }
        });
        dashboardView.getViewcreateCategoryMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new VieworEditTransactionCategoryDialog(user, DashboardController.this).showAndWait();
            }
        });
    }
}
