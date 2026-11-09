package com.wildtrail.modules.payments;

// CashPayment: Java class for the WildTrail application.
public final class CashPayment
extends Payment {
    public CashPayment(double amount) {
        super(amount);
    }

    @Override
    // Returns an existing value or related record.
    protected String getMethod() {
        return "CASH";
    }

    @Override
    // Processes the request according to existing rules.
    public PaymentResult process() {
        return new PaymentResult("CASH", "PENDING", this.getAmount(), this.transactionRef(), null, null, "Pay in cash to your driver on the safari day");
    }
}
