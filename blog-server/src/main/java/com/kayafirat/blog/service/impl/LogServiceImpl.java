package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.dto.LogResponse;
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

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LogServiceImpl  implements LogService {

    private final LogRepository logRepository;
    private final UserRepository userRepository;

    @Override
    public List<LogResponse> getLogs() {
        return logRepository.findAllResponse();
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
