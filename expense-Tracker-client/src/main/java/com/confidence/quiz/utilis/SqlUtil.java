package com.confidence.quiz.utilis;

import com.confidence.quiz.models.TransactionCategory;
import com.confidence.quiz.models.User;
import com.google.gson.*;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlUtil {
//    get
public static User getUserByEmail(String userEmail){
    HttpURLConnection conn = null;

    try {
        conn = ApiUtil.fetchApi(
                "/api/v1/user?email=" + userEmail,
                ApiUtil.RequestMethod.GET,
                null
        );

        if (conn.getResponseCode() != 200){
            System.out.println("Error(getUserByEmail): " + conn.getResponseCode());
            return null;
        }

        String userDataJson = ApiUtil.readApiResponase(conn);

        JsonObject jsonObject =
                JsonParser.parseString(userDataJson).getAsJsonObject();

        int id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        String email = jsonObject.get("email").getAsString();
        String password = jsonObject.get("password").getAsString();

        LocalDateTime createdAt =
                LocalDateTime.parse(jsonObject.get("created_at").getAsString());
        return new User(id, name, email, password, createdAt);

    } catch (IOException e){
        e.printStackTrace();

    } finally {
        if (conn != null){
            conn.disconnect();
        }
    }

    return null;
}

public static List<TransactionCategory> getAllTransactionCategoriesByUser (User user){
    HttpURLConnection conn = null;
    List<TransactionCategory> categories = new ArrayList<>();
    try {
        conn = ApiUtil.fetchApi(
                "/api/v1/transaction-category/user/" + user.getId(),
                ApiUtil.RequestMethod.GET, null
        );
        if (conn.getResponseCode() != 200) {
            System.out.println("Error(getTransactoncategoreiesByUser)" + conn.getResponseCode());
        };

            String result = ApiUtil.readApiResponase(conn);
        JsonArray resultJsonArray = new JsonParser().parse(result).getAsJsonArray();
            for (JsonElement jsonElement : resultJsonArray){
                int categoryId = jsonElement.getAsJsonObject().get("id").getAsInt();
                String categoryName = jsonElement.getAsJsonObject().get("categoryName").getAsString();
                String categoryColor = jsonElement.getAsJsonObject().get("categoryColor").getAsString();

                categories.add(new TransactionCategory(categoryId,categoryName,categoryColor));

        }
        return categories;
    }catch (IOException e){
        e.printStackTrace();
    }finally {
        if (conn != null){
            conn.disconnect();
        }

    }
    return null; // the account was successsfully creted and stored into our database

}
//    post

public  static boolean postLoginUser(String email, String password){
    HttpURLConnection conn = null;

    try {
        conn = ApiUtil.fetchApi(
                "/api/v1/user/login?email=" + email + "&password=" + password,
                ApiUtil.RequestMethod.POST, null
        );
        if (conn.getResponseCode() != 200) {
            System.out.println("failed to authenticate");
            return false;
        }
    }catch (IOException e){
        e.printStackTrace();
    }finally {
        if (conn != null){
            conn.disconnect();
        }
    }
    return true; // the account was successsfully creted and stored into our database
}

    public static boolean postCreateUser(JsonObject userData){
        HttpURLConnection conn = null;
        try {
            conn = ApiUtil.fetchApi("/api/v1/user", ApiUtil.RequestMethod.POST, userData);

            if (conn.getResponseCode() != 200) {
                return false; //failed to authenticate
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (conn != null){
                conn.disconnect();
            }
        }
        return true; // the account was successsfully creted and stored into our database

    }

    public static boolean PostTransactionCategory(JsonObject transactionCategoryData){
        HttpURLConnection conn = null;
        try {
            conn = ApiUtil.fetchApi("/api/v1/transaction-category" ,ApiUtil.RequestMethod.POST, transactionCategoryData );
            if (conn.getResponseCode() !=200){
                return false;
            }
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (conn != null){
                conn.disconnect();
            }
        }
        return false;
    }

//    update

    public static boolean putTransactionCategory(int categoryId, String newCategoryName, String newCategoryColor){
        HttpURLConnection conn = null;

        try {
            conn = ApiUtil.fetchApi(
                    "/api/v1/transaction-category/" + categoryId + "?newCategoryName=" + newCategoryName + "&newCategoryColor=" + newCategoryColor ,
                    ApiUtil.RequestMethod.PUT, null
            );
            if (conn.getResponseCode() != 200) {
                System.out.println("Erro(putTransactionCategory): " + conn.getResponseCode());
                return false;
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (conn != null){
                conn.disconnect();
            }

        }
        return true; // the account was successsfully creted and stored into our database

    }


//    delect

    public static boolean delectTransactionCategory(int categoryId){
        HttpURLConnection conn = null;

        try {
            conn = ApiUtil.fetchApi(
                    "/api/v1/transaction-category/" + categoryId,
                    ApiUtil.RequestMethod.DELETE, null
            );
            if (conn.getResponseCode() != 200) {
                System.out.println("Erro(delectTransactionCategory): " + conn.getResponseCode());
                return false;
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (conn != null){
                conn.disconnect();
            }

        }
        return true; // the account was successsfully creted and stored into our database

    }
}
