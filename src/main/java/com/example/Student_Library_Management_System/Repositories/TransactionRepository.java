package com.example.Student_Library_Management_System.Repositories;

import com.example.Student_Library_Management_System.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.event.TransactionalApplicationListenerMethodAdapter;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query(value = "select * from transaction where book_id=:bookId and card_id=:cardId and isIssueOperation=true",nativeQuery = true)
    List<Transaction> getTransactionForBookAndCard(Integer bookId, Integer cardId);
}
