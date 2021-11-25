package com.kayafirat.blog.service;

import com.kayafirat.blog.entity.Config;

public interface ConfigService {

    Config updateConfig(Config config);

    Config getConfig(String configKod);

}
