package org.seerbit.dto;


import jakarta.validation.constraints.NotBlank;

public record TransactionRequestDto(@NotBlank  String amount, @NotBlank  String timestamp) {
}
