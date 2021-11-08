package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.entity.Report;
import com.kayafirat.blog.exception.custom.EntityNotFoundException;
import com.kayafirat.blog.exception.custom.ReportNotFoundException;
import com.kayafirat.blog.repository.ReportRepository;
import com.kayafirat.blog.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;


    @Override
    @Cacheable(cacheNames = "report", key = "'all'")
    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "report", key = "#id")
    public Report getOne(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }

    @Override
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    @Override
    @Cacheable(cacheNames = "report", key = "#report.id")
    public Report update(Report report) {
        return reportRepository.save(report);
    }

    @Override
    @CacheEvict(value = "report", key = "#id")
    public void delete(Long id) {
        if(!reportRepository.existsById(id))
            throw new EntityNotFoundException("Silinmek istenen report bulunamadı");

        reportRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateReadStatus(Long id) {
        reportRepository.updateReadStatus(id);
    }
}
