package com.nackademin.superserver.rabbit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nackademin.superserver.service.MailService;
import com.nackademin.superserver.service.dto.PlainEmailReq;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Hodei Eceiza
 * Date: 9/2/2021
 * Time: 22:53
 * Project: superserver
 * Copyright: MIT
 */
@Component
public class RabbitConsumer {
    private final MailService mailService;

    public RabbitConsumer(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = "payments")
    public void receiveMeesage(String message) throws IOException {
        ObjectMapper o=new ObjectMapper();
        Payment payment=o.readValue(message,Payment.class);
        System.out.println("rabbit says-> " + payment.getStatus() + " "+  payment.getReference());
        if(payment.getStatus().equals("PAID")) {
            mailService.sendOnePlainEmail(new PlainEmailReq(payment.getReference(), "PAYMENT DONE", "your payment is done:\nid: " + payment.getPaymentId()));

        }
        else
            System.out.println("sending to server payments created");

    }
}
