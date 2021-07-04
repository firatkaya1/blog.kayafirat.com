package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.entity.Security;
import com.kayafirat.blog.repository.SecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityService {

    private final SecurityRepository securityRepository;

    public List<Security> getLinks(){
        return securityRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Security add(Security security){
        return securityRepository.save(security);
    }

}
