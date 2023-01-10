package com.dmit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageBox {
    String caption;
    String body;
    MessageBoxType type;

    public enum MessageBoxType {
        INFO,
        WARNING,
        ERROR,
        SUCCESS;
    }
}
