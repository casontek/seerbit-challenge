package org.seerbit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.seerbit.dto.TransactionRequestDto;
import org.seerbit.model.Transaction;
import org.seerbit.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    final TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<String> submitTransaction( @Valid @RequestBody TransactionRequestDto dto) {

        return transactionService.submitTransaction(dto);
    }

    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getTransactions() {

        return transactionService.getTransactions();
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteTransaction() {
        transactionService.deleteTransactions();

        return ResponseEntity.ok("Transactions deleted.");
    }

}
