package com.confidence.quiz.dialog;

import com.confidence.quiz.models.User;
import com.confidence.quiz.utilis.SqlUtil;
import com.confidence.quiz.utilis.Utilitie;
import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CreateNewCAtegoryDialog extends CustomDialog{
    private TextField newCategoryTextField;
    private ColorPicker colorPicker;
    private Button createCategoryBtn;

     public CreateNewCAtegoryDialog(User user) {
         super(user);
         setTitle("create new dialog");
         getDialogPane().setContent(createDialogContentBox());
     }

     private VBox createDialogContentBox(){
         VBox dialogContentBox = new VBox(20);

         newCategoryTextField = new TextField();
         newCategoryTextField.setPromptText("Enter Category Name");
         newCategoryTextField.getStyleClass().addAll("text-size-md", "field-background", "text-light-gray");

         colorPicker = new ColorPicker();
         colorPicker.getStyleClass().add("text-size-md");
         colorPicker.setMaxWidth(Double.MAX_VALUE);

         createCategoryBtn = new Button("Create");
         createCategoryBtn.getStyleClass().addAll("bg-light-blue", "text-size-md", "text-white");
         createCategoryBtn.setMaxWidth(Double.MAX_VALUE);
         createCategoryBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event) {

                 if(!validateInput()){
                     Utilitie.showAlertDialog(Alert.AlertType.ERROR, "Invalide Input");
                     return;
                 }
                 // extract the data
                 String categoryName = newCategoryTextField.getText();
                 String color = Utilitie.getHexColorValue(colorPicker);
                 System.out.println(color);

                 JsonObject userData = new JsonObject();
//                 System.out.println(userData);
                 userData.addProperty("id", user.getId());

                 JsonObject transactionCategoryData = new JsonObject();
                 transactionCategoryData.add("user", userData);
                 transactionCategoryData.addProperty("categoryName", categoryName);
                 transactionCategoryData.addProperty("categoryColor", color);

                 boolean postTransactionCategoryStatus = SqlUtil.PostTransactionCategory(transactionCategoryData);
                 if(postTransactionCategoryStatus){
                     Utilitie.showAlertDialog(Alert.AlertType.INFORMATION,
                             "Success: Create a Transaction Category");
                 }else{
                     Utilitie.showAlertDialog(Alert.AlertType.ERROR,
                             "Error: Failed to create Transaction Category");
                 }

             }
         });

         dialogContentBox.getChildren().addAll(newCategoryTextField, colorPicker ,createCategoryBtn);
         return dialogContentBox;
     }

    private boolean validateInput(){
        if(newCategoryTextField.getText().isEmpty()) return false;
        return true;
    }
 }
