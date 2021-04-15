package com.kayafirat.blog.service;

import com.kayafirat.blog.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();

    Contact getOne(Long id);

    Contact save(Contact report);

    Contact update(Contact entity);

    void delete(Long id);

    void updateReadStatus(Long id);
}
