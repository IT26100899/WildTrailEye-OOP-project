package com.wildtrail.modules.payments;

import java.time.Instant;

// PaymentResult: Java class for the WildTrail application.
// Creates a new PaymentResult object.
public record PaymentResult(String method, String status, double amount, String transactionRef, String cardLast4, Instant paidAt, String message) {
}
