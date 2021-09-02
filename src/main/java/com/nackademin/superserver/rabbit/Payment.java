package com.nackademin.superserver.rabbit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Hodei Eceiza
 * Date: 9/2/2021
 * Time: 23:10
 * Project: superserver
 * Copyright: MIT
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("paymentId")
    private String paymentId;
    @JsonProperty("status")
    private String status;
    @JsonCreator
    public Payment(@JsonProperty("reference") String reference, @JsonProperty("paymentId") String paymentId, @JsonProperty("status") String status) {
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
