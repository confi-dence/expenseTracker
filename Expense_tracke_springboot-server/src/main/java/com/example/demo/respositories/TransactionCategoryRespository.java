package com.example.demo.respositories;

import com.example.demo.entities.TransactionCategory;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionCategoryRespository extends JpaRepository<TransactionCategory, Integer> {
    List<TransactionCategory> findAllByUserId(int userId);
}
