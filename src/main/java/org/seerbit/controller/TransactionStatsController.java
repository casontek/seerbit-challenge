package org.seerbit.controller;

import lombok.RequiredArgsConstructor;
import org.seerbit.dto.TransactionStats;
import org.seerbit.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class TransactionStatsController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<TransactionStats> getTransactions() {

        return  transactionService.getTransactionStats();
    }

}
