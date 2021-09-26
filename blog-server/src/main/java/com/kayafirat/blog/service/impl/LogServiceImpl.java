package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.entity.Log;
import com.kayafirat.blog.entity.User;
import com.kayafirat.blog.exception.custom.UserNotFoundException;
import com.kayafirat.blog.repository.LogRepository;
import com.kayafirat.blog.repository.UserRepository;
import com.kayafirat.blog.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LogServiceImpl  implements LogService {

    private final LogRepository logRepository;
    private final UserRepository userRepository;

    @Override
    public Page<Log> getLogs(int pageNumber, int pageSize, String sortedBy, String orderBy) {
        Sort sort = orderBy.equals("asc".toLowerCase()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return logRepository.findAll(pageable);
    }

    @Override
    public void addLog(Log log) {
        if(SecurityContextHolder.getContext().getAuthentication().getName() != "anonymousUser"){
            User user = userRepository.findById(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotFoundException::new);
            String detail = "Isteği yapan kullanıcı :"+user.getId()+ " mail :"+user.getEmail()+ "  :"+log.getLogMessage();
            log.setLogMessage(detail);
        }
        logRepository.save(log);
    }
}
