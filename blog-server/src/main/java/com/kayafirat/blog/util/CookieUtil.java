package com.kayafirat.blog.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookieUtil {

    private final Environment env;

    public Cookie create(String value){
        Cookie cookie = new Cookie("authenticate", value);
        cookie.setMaxAge(Integer.parseInt(env.getProperty("cookie.default.max-age")));
        cookie.setHttpOnly(Boolean.parseBoolean(env.getProperty("cookie.default.httpOnly")));
        cookie.setSecure(Boolean.parseBoolean(env.getProperty("cookie.default.isSecure")));
        cookie.setDomain(env.getProperty("cookie.default.domain"));
        cookie.setPath(env.getProperty("cookie.default.path"));

        return cookie;
    }

    public Cookie delete(String value){
        Cookie cookie = new Cookie("authenticate", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(Boolean.parseBoolean(env.getProperty("cookie.default.httpOnly")));
        cookie.setSecure(Boolean.parseBoolean(env.getProperty("cookie.default.isSecure")));
        cookie.setDomain(env.getProperty("cookie.default.domain"));
        cookie.setPath(env.getProperty("cookie.default.path"));

        return cookie;
    }

}