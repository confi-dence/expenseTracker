package com.example.demo.controllers;

import com.example.demo.entities.TransactionCategory;
import com.example.demo.services.TransactionCategoryService;
import com.example.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/transaction-category")
public class TransactionCategoryController {
    private static final Logger logger = Logger.getLogger(TransactionCategoryController.class.getName());

    @Autowired
    private TransactionCategoryService transactonCategoryService;

//    Get

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionCategory>> getAllTransactionCategoriesByUserId (@PathVariable int userId){
        logger.info("getting all transactin categores form user:"+ userId);
        List<TransactionCategory> transactionCategories = transactonCategoryService.getAllTransactionCategoriesByUserId(userId);
        return  ResponseEntity.status(HttpStatus.OK).body(transactionCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionCategory> getTransactionCategoriesById (@PathVariable int id){
        logger.info("getting all transactin categores with id:"+ id);
        Optional<TransactionCategory> transactionCategoryOptional = transactonCategoryService.getTransactionCategoryById(id);
        if (transactionCategoryOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return  ResponseEntity.status(HttpStatus.OK).body(transactionCategoryOptional.get());
    }
//    post
    @PostMapping
    public ResponseEntity<TransactionCategory> createTransactonCategory(@RequestBody TransactionCategory transactionCategory){
        logger.info("Create Transaction Category for: " + transactionCategory.getCategoryName());

        transactonCategoryService.creatTransactionCategory(
                transactionCategory.getUser().getId(),
                transactionCategory.getCategoryName(),

                transactionCategory.getCategoryColor()
        );

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //update

    @PutMapping("/{id}")
    public ResponseEntity<TransactionCategory> updateTransactionCategoryById(@PathVariable int id, @RequestParam String newCategoryName, @RequestParam String newCategoryColor){
        logger.info("updatuing transaction category with id: " + id);
        TransactionCategory updatedTransactionCategory = transactonCategoryService.updateTransactionCategoryById(id, newCategoryName,newCategoryColor);
        if (updatedTransactionCategory == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedTransactionCategory);

    }
// delect
    @DeleteMapping("/{id}")

    public ResponseEntity<TransactionCategory> delectTransactionCategoryById(@PathVariable int id){
        logger.info("delect transaction category with id: " + id);
//        TransactionCategory delectTransactionCategoryById = transactonCategoryService.delectTransactionCategoryById(id);
        if (!transactonCategoryService.delectTransactionCategoryById(id)){
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
