package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.entity.Config;
import com.kayafirat.blog.exception.custom.EntityNotFoundException;
import com.kayafirat.blog.repository.ConfigRepository;
import com.kayafirat.blog.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConfigServiceImpl implements ConfigService {

    private final ConfigRepository configRepository;

    @Override
    public Config updateConfig(Config _config) {
        if(_config.getConfigId() != null){
            return configRepository.save(_config);
        }
        throw new EntityNotFoundException("Güncellenmek istenen config ID'si bulunamadı.");
    }

    @Override
    public Config getConfig(String configKod) {
       return configRepository.findByConfigKod(configKod).orElseThrow(() -> new EntityNotFoundException("Koda uygun config bulunamadı"));
    }
}
