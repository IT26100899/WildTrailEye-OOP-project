package com.wildtrail.modules.payments;

import com.wildtrail.exception.ApiException;
import java.util.concurrent.ThreadLocalRandom;

// Payment: Java class for the WildTrail application.
public abstract class Payment
implements PaymentProcessor {
    private final double amount;

    protected Payment(double amount) {
        if (!Double.isFinite(amount) || amount < 0.0) {
            throw ApiException.badRequest("Invalid payment amount");
        }
        this.amount = (double)Math.round(amount * 100.0) / 100.0;
    }

    // Returns an existing value or related record.
    public double getAmount() {
        return this.amount;
    }

    protected abstract String getMethod();

    // Handles transaction ref using the existing project logic.
    protected String transactionRef() {
        int random = ThreadLocalRandom.current().nextInt(10000, 100000);
        return "TXN-" + this.getMethod() + "-" + random + System.currentTimeMillis() % 1000L;
    }
}
