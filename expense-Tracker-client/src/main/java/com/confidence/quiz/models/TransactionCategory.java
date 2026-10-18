package com.confidence.quiz.models;

public class TransactionCategory {
    private int id;
    private String categoryName;
    private String CategoryColor;

    public TransactionCategory(int id, String categoryName, String categoryColor) {
        this.id = id;
        this.categoryName = categoryName;
        CategoryColor = categoryColor;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryColor() {
        return CategoryColor;
    }

    public void setCategoryColor(String categoryColor) {
        CategoryColor = categoryColor;
    }
}
