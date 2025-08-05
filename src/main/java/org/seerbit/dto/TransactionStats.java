package org.seerbit.dto;

public record TransactionStats(
        String sum,
        String avg,
        String max,
        String min,
        String count
) {
}
