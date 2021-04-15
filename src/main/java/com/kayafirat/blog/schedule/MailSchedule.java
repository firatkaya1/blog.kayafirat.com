package com.kayafirat.blog.schedule;

import com.kayafirat.blog.entity.MailQueue;
import com.kayafirat.blog.enums.Type;
import com.kayafirat.blog.service.MailService;
import com.kayafirat.blog.service.impl.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailSchedule {

    private final MailService mailService;
    private final SenderService senderService;

    @Scheduled(fixedDelay = 15000)
    public void sendMail() throws MessagingException {
        List<MailQueue> queueList = mailService.checkQueue();

        senderService.sendMail(Type.Verification, queueList.stream().filter(q -> q.getMailType().equals(Type.Verification)).collect(Collectors.toList()));
        senderService.sendMail(Type.VerificationSuccess, queueList.stream().filter(q -> q.getMailType().equals(Type.VerificationSuccess)).collect(Collectors.toList()));
        senderService.sendMail(Type.PasswordChanged, queueList.stream().filter(q -> q.getMailType().equals(Type.PasswordChanged)).collect(Collectors.toList()));
        senderService.sendMail(Type.PasswordChangedSuccess, queueList.stream().filter(q -> q.getMailType().equals(Type.PasswordChangedSuccess)).collect(Collectors.toList()));
        senderService.sendMail(Type.LoginSuccess, queueList.stream().filter(q -> q.getMailType().equals(Type.LoginSuccess)).collect(Collectors.toList()));
        senderService.sendMail(Type.LoginAttempt, queueList.stream().filter(q -> q.getMailType().equals(Type.LoginAttempt)).collect(Collectors.toList()));
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void clearMail() {
        mailService.deleteAll();
    }

}
