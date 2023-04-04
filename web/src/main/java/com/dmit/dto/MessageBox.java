package com.dmit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageBox {
    private final String caption;
    private final String body;
    private final MessageBoxType type;

    public enum MessageBoxType {
        INFO,
        WARNING,
        ERROR,
        SUCCESS;
    }
}
