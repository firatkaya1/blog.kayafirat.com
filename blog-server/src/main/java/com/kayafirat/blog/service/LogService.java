package com.kayafirat.blog.service;

import com.kayafirat.blog.dto.LogResponse;
import com.kayafirat.blog.entity.Log;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LogService {

    List<LogResponse> getLogs();

    public void addLog(Log log);

}
