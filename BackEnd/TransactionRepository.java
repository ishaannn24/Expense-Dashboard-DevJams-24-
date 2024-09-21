package com.example.moneymanagement.repository;

import com.example.moneymanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.email = ?1 AND t.modeOfPayment = ?2")
    BigDecimal calculateTotalSpentByUserAndMode(String email, String modeOfPayment);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.email = ?1")
    BigDecimal calculateTotalSpentByUser(String email);

    List<Transaction> findAllByUserEmail(String email);
}
