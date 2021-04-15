package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.entity.MailQueue;
import com.kayafirat.blog.exception.custom.MailNotFoundException;
import com.kayafirat.blog.repository.MailRepository;
import com.kayafirat.blog.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailServiceImpl implements MailService {

    private final MailRepository mailRepository;

    @Override
    public List<MailQueue> getAll() {
        return mailRepository.findAll();
    }

    @Override
    public MailQueue getOne(Long id) {
        return mailRepository.findById(id).orElseThrow(() -> new MailNotFoundException(id));
    }

    @Override
    public MailQueue save(MailQueue mailQueue) {
        return mailRepository.save(mailQueue);
    }

    @Override
    public List<MailQueue> save(List<MailQueue> mailQueue) {
        return mailRepository.saveAll(mailQueue);
    }

    @Override
    public MailQueue update(MailQueue mailQueue) {
        return mailRepository.save(mailQueue);
    }

    @Override
    public void delete(Long id) {
        mailRepository.deleteByQueueId(id);
    }

    @Override
    public void updateStatus(Long id) {
        mailRepository.updateStatus(id);
    }

    @Override
    public void updateStatus(List<MailQueue> mailQueues) {
        if (mailQueues.size() > 0)
            mailRepository.updateStatusAll(mailQueues.stream().mapToLong(h -> h.getQueueId()).toArray());
    }

    @Override
    public List<MailQueue> checkQueue() {
        return mailRepository.getMails();
    }

    @Override
    public void deleteAll() {
        mailRepository.deleteAllSend();
    }
}
