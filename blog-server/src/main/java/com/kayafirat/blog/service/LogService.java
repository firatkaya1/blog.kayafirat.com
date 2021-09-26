package com.kayafirat.blog.service;

import com.kayafirat.blog.entity.Log;
import com.kayafirat.blog.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

public interface LogService {

    Page<Log> getLogs(int pageNumber, int pageSize, String sortedBy, String orderBy);

    public void addLog(Log log);

}
