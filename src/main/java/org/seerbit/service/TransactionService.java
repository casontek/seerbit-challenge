package org.seerbit.service;

import org.seerbit.dto.TransactionRequestDto;
import org.seerbit.dto.TransactionStats;
import org.seerbit.model.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionService {
    ResponseEntity<String> submitTransaction(TransactionRequestDto dto);

    ResponseEntity<List<Transaction>> getTransactions();

    ResponseEntity<TransactionStats> getTransactionStats();

    void deleteTransactions();

}
