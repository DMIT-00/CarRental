package com.dmit.entity.order;

/**
 * PAYMENT - waiting for client payment <br>
 * PAID - paid and waiting for client to take the car <br>
 * CAR_IN_USE - client has the car <br>
 * CAR_RETURNED - car returned <br>
 * CLOSED - order is closed <br>
 */
public enum OrderStatus {
    PAYMENT, PAID, CAR_IN_USE, CAR_RETURNED, CLOSED;
}
