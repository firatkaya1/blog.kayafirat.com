package com.kayafirat.blog.util;

import javax.mail.Session;
import java.util.Properties;

public class Auth {

    public static Session getSession() {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.yandex.com");
        props.put("mail.smtp.user", "noreply@kayafirat.com");
        props.put("mail.smtp.password", "bE99321605848");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        return Session.getDefaultInstance(props);
    }


}
