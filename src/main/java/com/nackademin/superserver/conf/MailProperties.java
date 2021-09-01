package com.nackademin.superserver.conf;

import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Hodei Eceiza
 * Date: 9/1/2021
 * Time: 22:25
 * Project: superserver
 * Copyright: MIT
 */
@Component
public class MailProperties {
    @Value("${twillio.api.key}")
    private String apiKey;
    @Value("${twillio.from}")
    private String fromEmail;
    private SendGrid sendGrid;

    public String getApiKey(){
      return apiKey;
    }

    public Email getFromEmail() {
        return new Email(fromEmail);
    }
    public SendGrid getSendGrid(){
        return new SendGrid(apiKey);
    }

}
