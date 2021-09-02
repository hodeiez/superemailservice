package com.nackademin.superserver.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Hodei Eceiza
 * Date: 9/2/2021
 * Time: 00:13
 * Project: superserver
 * Copyright: MIT
 */
@AllArgsConstructor
@Data
public class TemplateEmailReq {
    private String toEmail;
    private String subject;
    private String content;
    private String name;

}