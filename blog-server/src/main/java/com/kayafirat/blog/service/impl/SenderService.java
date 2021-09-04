package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.entity.MailQueue;
import com.kayafirat.blog.enums.Type;
import com.kayafirat.blog.service.MailService;
import com.kayafirat.blog.util.Auth;
import com.kayafirat.blog.util.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SenderService {

    private final Template template;
    private final MailService mailService;

    public void sendMail(Type type, List<MailQueue> to) throws MessagingException {
        switch (type) {
            case Verification:
                sendVerification(to);
                break;
            case VerificationSuccess:
                sendVerificationSuccess(to);
                break;
            case PasswordChanged:
                sendPasswordChange(to);
                break;
            case PasswordChangedSuccess:
                sendPasswordChangeSuccess(to);
                break;
            case LoginSuccess:
                sendLoginSuccess(to);
                break;
            case LoginAttempt:
                sendLoginAttempt(to);
                break;
            case Other:
                sendOther(to);
                break;
            default:
                System.out.println("başarısız");
        }
    }


    public void sendPasswordChange(List<MailQueue> to) throws MessagingException {
        Session session = Auth.getSession();
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.yandex.com", "noreply@kayafirat.com", "bE99321605848");

        for (int i = 0; i < to.size(); i++) {
            MimeMessage message = template.resetPasswordTemplate(to.get(i).getEmailAddress(), session);
            transport.sendMessage(message, message.getAllRecipients());
            to.get(i).setSend(true);
            to.get(i).setSendDate(new Date());
        }
        transport.close();
        mailService.updateStatus(to);
    }

    public void sendPasswordChangeSuccess(List<MailQueue> to) throws MessagingException {
        Session session = Auth.getSession();
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.yandex.com", "noreply@kayafirat.com", "bE99321605848");

        for (int i = 0; i < to.size(); i++) {
            MimeMessage message = template.passwordChangedTemplate(to.get(i).getEmailAddress(), session);
            transport.sendMessage(message, message.getAllRecipients());
            to.get(i).setSend(true);
            to.get(i).setSendDate(new Date());
        }
        transport.close();
        mailService.updateStatus(to);

    }

    public void sendVerification(List<MailQueue> to) throws MessagingException {
        Session session = Auth.getSession();
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.yandex.com", "noreply@kayafirat.com", "bE99321605848");
        for (int i = 0; i < to.size(); i++) {
            MimeMessage message = template.verificationEmailTemplate(to.get(i).getEmailAddress(), session);
            transport.sendMessage(message, message.getAllRecipients());
            to.get(i).setSend(true);
            to.get(i).setSendDate(new Date());
        }
        transport.close();
        mailService.updateStatus(to);
    }

    public void sendVerificationSuccess(List<MailQueue> to) throws MessagingException {
        Session session = Auth.getSession();
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.yandex.com", "noreply@kayafirat.com", "bE99321605848");

        for (int i = 0; i < to.size(); i++) {
            MimeMessage message = template.verificationSuccessTemplate(to.get(i).getEmailAddress(), session);
            transport.sendMessage(message, message.getAllRecipients());
            to.get(i).setSend(true);
            to.get(i).setSendDate(new Date());
        }
        transport.close();
        mailService.updateStatus(to);
    }

    public void sendLoginSuccess(List<MailQueue> to) throws MessagingException {
        Session session = Auth.getSession();
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.yandex.com", "noreply@kayafirat.com", "bE99321605848");
        for (int i = 0; i < to.size(); i++) {
            MimeMessage message = template.loginSuccessTemplate(to.get(i).getEmailAddress(), session);
            transport.sendMessage(message, message.getAllRecipients());
            to.get(i).setSend(true);
            to.get(i).setSendDate(new Date());
        }
        transport.close();
        mailService.updateStatus(to);

    }

    public void sendLoginAttempt(List<MailQueue> to) throws MessagingException {
        Session session = Auth.getSession();
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.yandex.com", "noreply@kayafirat.com", "bE99321605848");
        for (int i = 0; i < to.size(); i++) {
            MimeMessage message = template.loginAttemptTemplate(to.get(i).getEmailAddress(), session);
            transport.sendMessage(message, message.getAllRecipients());
            to.get(i).setSend(true);
            to.get(i).setSendDate(new Date());
        }
        transport.close();
        mailService.updateStatus(to);
    }

    public void sendOther(List<MailQueue> to) throws  MessagingException {
        Session session = Auth.getSession();
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.yandex.com", "noreply@kayafirat.com", "bE99321605848");
        for (int i = 0; i < to.size(); i++) {
            MimeMessage message = template.other(to.get(i).getEmailAddress(), session,to.get(i).getMailTitle(),to.get(i).getMailSubtitle(),to.get(i).getBody());
            transport.sendMessage(message, message.getAllRecipients());
            to.get(i).setSend(true);
            to.get(i).setSendDate(new Date());
        }
        transport.close();
        mailService.updateStatus(to);
    }

}