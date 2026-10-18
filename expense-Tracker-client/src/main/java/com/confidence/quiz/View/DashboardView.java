package com.confidence.quiz.View;


import com.confidence.quiz.controllers.DashboardController;
import com.confidence.quiz.utilis.Utilitie;
import com.confidence.quiz.utilis.ViewNavigator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class DashboardView {


    private String email;
    private Label currentBalanceLabel, currentBalance;
    private Label totalIncomeLabel, totalIncome;
    private Label totalExpenseLabel, totalExpense;
    private MenuItem createCategoryMenuItem;
    private MenuItem ViewcreateCategoryMenuItem;


    public MenuItem getCreateCategoryMenuItem() {
        return createCategoryMenuItem;
    }

    public void setCreateCategoryMenuItem(MenuItem createCategoryMenuItem) {
        this.createCategoryMenuItem = createCategoryMenuItem;
    }
    public String getEmail() {
        return email;
    }

    public MenuItem getViewcreateCategoryMenuItem() {
        return ViewcreateCategoryMenuItem;
    }

    public void setViewcreateCategoryMenuItem(MenuItem viewcreateCategoryMenuItem) {
        ViewcreateCategoryMenuItem = viewcreateCategoryMenuItem;
    }

    public DashboardView(String email) {
        this.email = email;


        currentBalanceLabel = new Label("Current Balance:");
        totalIncomeLabel = new Label("Total Income:");
        totalExpenseLabel = new Label("Total Expense:");

        currentBalance = new Label("56");
        totalIncome = new Label("56");
        totalExpense = new Label("56");
    }
    public void show(){
        Scene scene = createScene();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        ViewNavigator.switchViews(scene);
        new DashboardController(this);
    }

    private Scene createScene() {

        MenuBar menuBar = createMenuBar();
        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.getStyleClass().addAll("main-background");

        HBox balanceSummaryBox = createBalanceSummaryBox();
        mainContainer.getChildren().addAll(menuBar, balanceSummaryBox);
        return new Scene(mainContainer, Utilitie.App_Width, Utilitie.App_Height);

    }

    private MenuBar createMenuBar(){

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        createCategoryMenuItem = new MenuItem("Create Category");
        ViewcreateCategoryMenuItem = new MenuItem("View Categories");

        fileMenu.getItems().addAll(createCategoryMenuItem,ViewcreateCategoryMenuItem);
        menuBar.getMenus().addAll(fileMenu);
        return menuBar;

    }

    private HBox createBalanceSummaryBox() {
        HBox balanceSummaryBox = new HBox();

        VBox currentBalanceBox = new VBox();
        currentBalanceLabel.getStyleClass().addAll("text-size-lg", "text-light-gray");
        currentBalance.getStyleClass().addAll("text-size-lg", "text-white");
        currentBalanceBox.getChildren().addAll(currentBalanceLabel, currentBalance);

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        VBox  totalIncomeBox= new VBox();
        totalIncomeLabel.getStyleClass().addAll("text-size-lg", "text-light-gray");
        totalIncome.getStyleClass().addAll("text-size-lg", "text-white");
        totalIncomeBox.getChildren().addAll(totalIncomeLabel, totalIncome);

        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);

        VBox totalExpenseBox = new VBox();
        totalExpenseLabel.getStyleClass().addAll("text-size-lg", "text-light-gray");
        totalExpense.getStyleClass().addAll("text-size-lg", "text-white");
        totalExpenseBox.getChildren().addAll(totalExpenseLabel, totalExpense);




        balanceSummaryBox.getChildren().addAll(currentBalanceBox, region1,totalIncomeBox,region2, totalExpenseBox);
        return  balanceSummaryBox;
    }
}
