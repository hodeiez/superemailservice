package com.nackademin.superserver.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Hodei Eceiza
 * Date: 9/1/2021
 * Time: 23:17
 * Project: superserver
 * Copyright: MIT
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlainEmailReq {
    private String toEmail;
    private String subject;
    private String content;
}
