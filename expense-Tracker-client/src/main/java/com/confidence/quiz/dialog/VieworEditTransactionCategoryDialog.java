package com.confidence.quiz.dialog;

import com.confidence.quiz.components.CategoryComponent;
import com.confidence.quiz.controllers.DashboardController;
import com.confidence.quiz.models.TransactionCategory;
import com.confidence.quiz.models.User;
import com.confidence.quiz.utilis.SqlUtil;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;


public class VieworEditTransactionCategoryDialog extends CustomDialog{
    private DashboardController dashboardController;
    public VieworEditTransactionCategoryDialog(User user, DashboardController dashboardController) {
        super(user);

        this.dashboardController = dashboardController;
        setTitle("View Categories");
        setWidth(815);
        setHeight(500);

        ScrollPane mainContainer = createMainContainerContent();
        getDialogPane().setContent(mainContainer);
    }

    private ScrollPane createMainContainerContent() {
        VBox dialogpVBox = new VBox(20);
        ScrollPane scrollPane = new ScrollPane(dialogpVBox);
        scrollPane.setFitToWidth(true);
        List<TransactionCategory> transactionCategories = SqlUtil.getAllTransactionCategoriesByUser(user);
        for (TransactionCategory transactionCategory: transactionCategories){
            CategoryComponent categoryComponent = new CategoryComponent(dashboardController,transactionCategory);
            dialogpVBox.getChildren().add(categoryComponent);
        }
        return scrollPane;
    }

}





