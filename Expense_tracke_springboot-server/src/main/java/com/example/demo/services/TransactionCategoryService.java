package com.example.demo.services;

import com.example.demo.entities.TransactionCategory;
import com.example.demo.entities.User;
import com.example.demo.respositories.TransactionCategoryRespository;
import com.example.demo.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TransactionCategoryService {

    @Autowired
    private TransactionCategoryRespository transactioncategoryrespository;
    @Autowired
    private UserRepository userRepository;

    private final Logger logger = Logger.getLogger(TransactionCategoryService.class.getName());

    // get
    public Optional<TransactionCategory> getTransactionCategoryById(int id){
        logger.info("Getting transaction category by id:"+ id);
        return transactioncategoryrespository.findById(id);

    }
    public List<TransactionCategory> getAllTransactionCategoriesByUserId(int userId){
        logger.info("getting all trabnsaction categores from user" + userId);
        return transactioncategoryrespository.findAllByUserId(userId);
    }

//    post


    public TransactionCategory creatTransactionCategory(int userid, String categoryName, String categoryColor){
        logger.info("Create Transaction Category with user:"+ userid);

        Optional<User> user = userRepository.findById(userid);
        if (user.isEmpty()) return null;

        TransactionCategory transactionCategory = new TransactionCategory();
        transactionCategory.setUser(user.get());
        transactionCategory.setCategoryName(categoryName);
        transactionCategory.setCategoryColor(categoryColor);

        return transactioncategoryrespository.save(transactionCategory);
    }

    // update(put)
    public TransactionCategory updateTransactionCategoryById(int transactionCategoryId , String newCategoryName, String newCategoryColor){
        logger.info("Updating transaction category with Id: "+ transactionCategoryId);

        Optional<TransactionCategory> transactionCategoryOptional = transactioncategoryrespository.findById(transactionCategoryId);
        if (transactionCategoryOptional.isEmpty()){
            return null;
        }

        TransactionCategory updatedTransactionCategory = transactionCategoryOptional.get();
        updatedTransactionCategory.setCategoryName(newCategoryName);
        updatedTransactionCategory.setCategoryColor(newCategoryColor);
        return transactioncategoryrespository.save(updatedTransactionCategory);

    }

    //delect
    public boolean  delectTransactionCategoryById(int transactionCategoryId){
        logger.info("delecting transaction category with Id: "+ transactionCategoryId);
        Optional<TransactionCategory> transactionCategoryOptional = transactioncategoryrespository.findById(transactionCategoryId);
        if (transactionCategoryOptional.isEmpty()) return false;


        transactioncategoryrespository.delete(transactionCategoryOptional.get());
        return true;
    }
}
