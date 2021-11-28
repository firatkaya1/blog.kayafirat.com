package com.kayafirat.blog.util;


import javax.mail.Session;
import java.util.Properties;


public class Auth {



    public static Session getSession(String username,String password) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.yandex.com");
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        return Session.getDefaultInstance(props);
    }


}
