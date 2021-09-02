package com.nackademin.superserver.service.dto;

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
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateEmailReq {
    private String toEmail;
    private String subject;
    private String content;
    private String name;
    private List<String> ccs;

}
