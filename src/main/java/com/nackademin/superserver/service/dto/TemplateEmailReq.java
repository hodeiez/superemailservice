package com.nackademin.superserver.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 9/2/2021
 * Time: 00:13
 * Project: superserver
 * Copyright: MIT
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateEmailReq {
    private String toEmail;
    private String subject;
    private String content;
    private String name;
    private List<String> ccs;
    @JsonCreator
    public TemplateEmailReq(String toEmail, String subject, String content, String name, List<String> ccs) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.content = content;
        this.name = name;
        this.ccs = ccs;
    }

    public String getToEmail() {
        return toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public List<String> getCcs() {
        return ccs;
    }
}
