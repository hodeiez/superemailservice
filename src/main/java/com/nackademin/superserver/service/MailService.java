package com.nackademin.superserver.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nackademin.superserver.conf.MailProperties;
import com.nackademin.superserver.service.dto.PlainEmailReq;
import com.nackademin.superserver.service.dto.TemplateEmailReq;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public String sendTemplate(TemplateEmailReq plainEmail) throws IOException {
        String subject= plainEmail.getSubject();

        Mail mail=new Mail();
        Email from =mailProperties.getFromEmail();

        Email to=new Email(plainEmail.getToEmail());

        TestDynamicTemplatePersonalization per=new TestDynamicTemplatePersonalization();
        per.addTo(to);
        mail.setFrom(from);
        mail.setSubject(subject);
        per.addDynamicTemplateData("first_name",plainEmail.getName());
        per.addDynamicTemplateData("content",plainEmail.getContent());



        mail.addPersonalization(per);
        mail.setTemplateId("d-4628d722ae3647c385d42cb6ada6ba95"); //template id add to properties?? create an enum??


        Request req=new Request();
        req.setMethod(Method.POST);
        req.setEndpoint("mail/send");
        req.setBody(mail.build());

        Response resp= mailProperties.getSendGrid().api(req);

        return resp.getBody();

    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class TestDynamicTemplatePersonalization extends Personalization {

       // @JsonProperty(value = "dynamic_template_data")
        private Map<String, Object> dynamic_template_data;
        private Map<String, Object> cc;

        public void addCC(List<String> values){
            if (cc == null) {
                cc = new HashMap<String, Object>();
            }
            values.forEach(v->cc.put("cc",values));

        }
       // @JsonProperty("dynamic_template_data")
        public Map<String,Object> getDynamicTemplateData() {
            if (dynamic_template_data == null) {
                return Collections.<String, Object>emptyMap();
            }
            return dynamic_template_data;
        }




        public void addDynamicTemplateData(String key, String value) {
            if (dynamic_template_data == null) {
                dynamic_template_data = new HashMap<String, Object>();
            }
            dynamic_template_data.put(key, value);
        }
    }
}
