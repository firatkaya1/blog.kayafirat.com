package com.kayafirat.blog.controller;

import com.kayafirat.blog.service.ReportService;
import com.kayafirat.blog.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/report")
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReportController {

    private final ReportService reportService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(reportService.getAll());
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam Long id) {
        return ResponseEntity.ok(reportService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Report report) {
        reportService.save(report);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Report report) {
        return ResponseEntity.ok(reportService.update(report));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        reportService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "/status")
    public ResponseEntity<?> updateReadStatus(@RequestParam Long id) {
        reportService.updateReadStatus(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
