package com.nackademin.superserver.api;

import com.nackademin.superserver.service.MailService;
import com.nackademin.superserver.service.dto.PlainEmailReq;
import com.nackademin.superserver.service.dto.TemplateEmailReq;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by Hodei Eceiza
 * Date: 9/1/2021
 * Time: 21:50
 * Project: superserver
 * Copyright: MIT
 */
@RestController
@RequestMapping("/mail")
@AllArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/sendtest")
    public ResponseEntity<?> sendTest(){

        try {
            mailService.sendTestEmail();
            return ResponseEntity.ok().body("sent");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
    @PostMapping("plainmail")
    public ResponseEntity<?> sendPlainEmail(@RequestBody PlainEmailReq plainEmail){
        try {
            mailService.sendOnePlainEmail(plainEmail);
            return ResponseEntity.ok().body("sent to "+ plainEmail.getToEmail());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping("templatemail")
    public ResponseEntity<?> sendTemplateEmail(@RequestBody TemplateEmailReq templateEmail){
        try {
            mailService.sendTemplate(templateEmail);
            return ResponseEntity.ok().body("sent to "+ templateEmail.getToEmail());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
