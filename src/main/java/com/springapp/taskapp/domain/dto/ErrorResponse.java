package com.springapp.taskapp.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
