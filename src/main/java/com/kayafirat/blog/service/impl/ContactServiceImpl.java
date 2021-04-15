package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.entity.Contact;
import com.kayafirat.blog.exception.custom.ContactNotFoundException;
import com.kayafirat.blog.repository.ContactRepository;
import com.kayafirat.blog.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;


    @Override
    @Cacheable(cacheNames = "contact", key = "'all'")
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "contact", key = "#id")
    public Contact getOne(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    @Cacheable(cacheNames = "contact", key = "#contact.id")
    public Contact update(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    @CacheEvict(value = "contact", key = "#id")
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }

    @Transactional
    @Override
    @CacheEvict(value = "contact", key = "#id")
    public void updateReadStatus(Long id) {
        contactRepository.updateReadStatus(id);
    }
}
