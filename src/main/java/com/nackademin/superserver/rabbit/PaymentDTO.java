package com.nackademin.superserver.rabbit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Hodei Eceiza
 * Date: 9/2/2021
 * Time: 23:23
 * Project: superserver
 * Copyright: MIT
 */
public class PaymentDTO {
    @JsonProperty("reference")
    private final String reference;
    @JsonProperty("paymentId")
    private final String paymentId;
    @JsonProperty("status")
    private final String status;

    @JsonCreator
    public PaymentDTO(@JsonProperty("reference") String reference, @JsonProperty("paymentId") String paymentId, @JsonProperty("status") String status) {
        this.reference = reference;
        this.paymentId = paymentId;
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getStatus() {
        return status;
    }
}