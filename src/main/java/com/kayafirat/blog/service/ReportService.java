package com.kayafirat.blog.service;

import com.kayafirat.blog.entity.Report;

import java.util.List;

public interface ReportService  {
    List<Report> getAll();

    Report getOne(Long id);

    Report save(Report report);

    Report update(Report entity);

    void delete(Long id);

    void updateReadStatus(Long id);
}
