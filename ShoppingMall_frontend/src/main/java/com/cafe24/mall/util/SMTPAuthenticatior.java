package com.cafe24.mall.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import com.cafe24.mall.datasource.DataSource;
 
public class SMTPAuthenticatior extends Authenticator{
 
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(
        		DataSource.data.getNaversmtp().getSenderEmail(),
        		DataSource.data.getNaversmtp().getSenderEmailPassword()
        );
    }
}
