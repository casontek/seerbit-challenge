package org.seerbit.service;


import org.seerbit.dto.TransactionRequestDto;
import org.seerbit.dto.TransactionStats;
import org.seerbit.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TransactionSeviceImpl implements TransactionService {
    List<Transaction> transactions = new ArrayList<>();

    @Override
    public ResponseEntity<String> submitTransaction(TransactionRequestDto dto) {
        try {
            long currentTime = Instant.now().getEpochSecond();
            long transactionTime = Instant.parse(dto.timestamp()).getEpochSecond();
            System.out.println(Instant.now());

            if(transactionTime - currentTime > 5) {
                return new ResponseEntity<>("", HttpStatus.UNPROCESSABLE_ENTITY);
            }
            else if(currentTime - transactionTime > 30) {

                return new ResponseEntity<>("", HttpStatus.UNPROCESSABLE_ENTITY);
            }
            else  {
                //save transaction
                transactions.add(new Transaction(
                        transactions.size() + 1,
                        dto.amount(),
                        Instant.parse(dto.timestamp()))
                );

                return new ResponseEntity<>("", HttpStatus.CREATED);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>("", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity<List<Transaction>> getTransactions() {
       final var recentTransactions  = transactions.stream().filter(fx -> fx.getTimestamp()
                       .isBefore(Instant.now().minusSeconds(30))).toList();

       return new ResponseEntity<>(recentTransactions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TransactionStats> getTransactionStats() {
        final var rTransactions  = transactions.stream().filter(fx -> fx.getTimestamp()
                .isBefore(Instant.now().minusSeconds(30))).toList();

        final var amounts = rTransactions.stream().map(fx -> Double.parseDouble(fx.getAmount())).toList();

        final var stats = new TransactionStats(
                String.valueOf(amounts.stream().mapToDouble(Double::doubleValue).sum()),
                String.valueOf(amounts.stream().mapToDouble(Double::doubleValue).average().orElse(0.0)),
                String.valueOf(amounts.stream().mapToDouble(Double::doubleValue).max().orElse(0.0)),
                String.valueOf(amounts.stream().mapToDouble(Double::doubleValue).min().orElse(0.0)),
                String.valueOf(amounts.size())
        );

        return ResponseEntity.ok(stats);
    }

    @Override
    public void deleteTransactions() {
        transactions.clear();
    }

}
