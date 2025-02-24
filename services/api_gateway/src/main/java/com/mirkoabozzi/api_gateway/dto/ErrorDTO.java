package com.mirkoabozzi.api_gateway.dto;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime timeStamp) {
}
