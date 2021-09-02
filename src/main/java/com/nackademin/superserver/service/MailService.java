package com.nackademin.superserver.service;


import com.nackademin.superserver.conf.MailProperties;
import com.nackademin.superserver.service.dto.PlainEmailReq;
import com.nackademin.superserver.service.dto.TemplateEmailReq;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;



/**
 * Created by Hodei Eceiza
 * Date: 9/1/2021
 * Time: 21:35
 * Project: superserver
 * Copyright: MIT
 */
@AllArgsConstructor
@Service
public class MailService {

       private final MailProperties mailProperties;
       private final MailHelper mailHelper;
    public String sendTestEmail() throws IOException {
        //testing multiple recipients
        Mail mail=new Mail();
        Email from=mailProperties.getFromEmail();
        String subject= "test";
        Content content= new Content("text/plain","This is a test");
        Email to=new Email();
        to.setName("name");
        to.setEmail("tester@gmail.com");
        Personalization per=new Personalization();
        to.setEmail("tester2@hotmail.com");
        per.addTo(to);

        mail.setFrom(from);
        mail.setSubject(subject);
        mail.addContent(content);
        mail.addPersonalization(per);

        //Mail mail= new Mail(from,subject,to,content);
        mail.addPersonalization(per);
        Request req=new Request();
        req.setMethod(Method.POST);
        req.setEndpoint("mail/send");
        req.setBody(mail.build());

        Response resp= mailProperties.getSendGrid().api(req);

        return resp.getBody();

    }
    public String sendOnePlainEmail(PlainEmailReq plainEmail) throws IOException {



        Email from =mailProperties.getFromEmail();

        String subject= plainEmail.getSubject();

        Email to=new Email(plainEmail.getToEmail());

        Content content= new Content("text/plain",plainEmail.getContent());

        Mail mail= new Mail(from,subject,to,content);

        Request req=new Request();
        req.setMethod(Method.POST);
        req.setEndpoint("mail/send");
        req.setBody(mail.build());

        Response resp= mailProperties.getSendGrid().api(req);

        return resp.getBody();

    }
    public String sendTemplate(TemplateEmailReq templateEmail) throws IOException {
        String subject= templateEmail.getSubject();

        Mail mail=new Mail();
        Email from =mailProperties.getFromEmail();

        Email to=new Email(templateEmail.getToEmail());

        mailHelper.addTo(to);
        //per.addTo(to);

        mail.setFrom(from);
        mail.setSubject(subject);
        mailHelper.addDynamicTemplateData("first_name",templateEmail.getName());
        mailHelper.addDynamicTemplateData("content",templateEmail.getContent());
        templateEmail.getCcs().forEach(s-> mailHelper.addCc(new Email(s)));


        mail.addPersonalization(mailHelper);
        mail.setTemplateId("d-4628d722ae3647c385d42cb6ada6ba95"); //template id add to properties?? create an enum??


        Request req=new Request();
        req.setMethod(Method.POST);
        req.setEndpoint("mail/send");
        req.setBody(mail.build());

        Response resp= mailProperties.getSendGrid().api(req);

        return resp.getBody();

    }

}
