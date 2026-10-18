package com.confidence.quiz.components;

import com.confidence.quiz.controllers.DashboardController;
import com.confidence.quiz.models.TransactionCategory;
import com.confidence.quiz.utilis.SqlUtil;
import com.confidence.quiz.utilis.Utilitie;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CategoryComponent  extends HBox {

    private DashboardController dashboardController;
    private TransactionCategory transactionCategory;

    private TextField categoryTextField;
    private ColorPicker colorPicker;
    private Button editButton, saveButton, delectButton;

    private Boolean isEditing = false;

    public CategoryComponent(DashboardController dashboardController, TransactionCategory transactionCategory){
        this.dashboardController = dashboardController;
        this.transactionCategory = transactionCategory;

        setSpacing(10);
        setAlignment(Pos.CENTER_LEFT);
        getStyleClass().addAll("rounded-border", "field-background", "padding-10px");

        categoryTextField = new TextField();
        categoryTextField.setText(transactionCategory.getCategoryName());
        categoryTextField.setMaxWidth(Double.MAX_VALUE);
        categoryTextField.setEditable(false);
        HBox.setHgrow(categoryTextField, Priority.ALWAYS);
        categoryTextField.getStyleClass().addAll("field-background","text-size-md", "text-light-gray");

        colorPicker = new ColorPicker();
        colorPicker.setDisable(true);
        colorPicker.setValue(Color.valueOf(transactionCategory.getCategoryColor()));
        colorPicker.getStyleClass().addAll("text-size-sm");


        editButton = new Button("Edit");
        editButton.setMinWidth(50);
        editButton.getStyleClass().addAll("text-size-sm");
        editButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleToggle();
            }
        });


        saveButton = new Button("Save");
        saveButton.setMinWidth(50);
        saveButton.getStyleClass().addAll("text-size-sm");
        saveButton.setVisible(false);
        saveButton.setManaged(false);
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleToggle();
                // extract data
                String newCategoryName = categoryTextField.getText();
                String newCategoryColor = Utilitie.getHexColorValue(colorPicker);

                //update database
                SqlUtil.putTransactionCategory(transactionCategory.getId(), newCategoryName, newCategoryColor);
            }
        });


        delectButton = new Button("Delect");
        delectButton.setMinWidth(50);
        delectButton.getStyleClass().addAll("tex-size-sm", "bg-light-red", "text-white");
        delectButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!SqlUtil.delectTransactionCategory(transactionCategory.getId())){
                    return;
                }

                // remobve this componet from dialog
                setVisible(false);
                setManaged(false);

                if(getParent() instanceof VBox){
                    ((VBox) getParent()).getChildren().remove(CategoryComponent.this);
                }
            }
        });

        getChildren().addAll(categoryTextField,colorPicker,editButton,saveButton,delectButton);
    }
    private void handleToggle(){
        if(!isEditing){
            isEditing = true;
            categoryTextField.setEditable(true);
            categoryTextField.setStyle("-fx-background-color: #fff; -fx-text-fill: #000");

            // enable the color picker
            colorPicker.setDisable(false);

            // hide the edit button
            editButton.setVisible(false);
            editButton.setManaged(false);

            // display the save button
            saveButton.setVisible(true);
            saveButton.setManaged(true);

        }else{
            isEditing = false;

            //disable category teext feld
            categoryTextField.setEditable(false);
            categoryTextField.setStyle("-fx-background-color: #515050; -fx-text-fill: #BEB9B9");

            // diable the color picker
            colorPicker.setDisable(true);

            // disaplay the edit button
            editButton.setVisible(true);
            editButton.setManaged(true);

            // hide the save button
            saveButton.setVisible(false);
            saveButton.setManaged(false);
        }
    }
}
