package com.mirkoabozzi.product_service.dto;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime timeStamp) {
}
