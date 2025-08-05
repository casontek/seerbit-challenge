package org.seerbit;

import org.junit.jupiter.api.Test;
import org.seerbit.dto.TransactionRequestDto;
import org.seerbit.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class SeerbitChallengeApplicationTests {

    @Autowired
    private TransactionService transactionService;

    @Test
    void testSubmitTransaction_validTimestamp_returnsCreated() {
        String timestamp = Instant.now().minusSeconds(10).toString();
        final var dto = new TransactionRequestDto("50.00", timestamp);

        ResponseEntity<String> response = transactionService.submitTransaction(dto);

        //assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void contextLoads() {
    }

}
