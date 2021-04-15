package com.kayafirat.blog.service;

import com.kayafirat.blog.entity.MailQueue;

import java.util.List;

public interface MailService {

    List<MailQueue> getAll();

    MailQueue getOne(Long id);

    MailQueue save(MailQueue mailQueue);

    List<MailQueue> save(List<MailQueue> mailQueue);

    MailQueue update(MailQueue mailQueue);

    void delete(Long id);

    void deleteAll();

    void updateStatus(Long id);

    void updateStatus(List<MailQueue> mailQueues);

    List<MailQueue> checkQueue();
}
