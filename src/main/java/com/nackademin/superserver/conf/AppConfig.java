package com.nackademin.superserver.conf;

import com.nackademin.superserver.service.MailHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Hodei Eceiza
 * Date: 9/1/2021
 * Time: 21:37
 * Project: superserver
 * Copyright: MIT
 */
@Configuration
public class AppConfig {

    @Bean
    public MailProperties getMailProperties(){
        return new MailProperties();
    }
    @Bean
    public MailHelper geMailHelper(){
        return new MailHelper();
    }
}
