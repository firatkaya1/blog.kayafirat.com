package com.kayafirat.blog.config;

import com.kayafirat.blog.security.JwtRequestFilter;
import com.kayafirat.blog.service.impl.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private Environment environment;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors().configurationSource(corsConfigurationSource()).and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST,"/user/login","/user/forgot","/user","/user/oauth/**").permitAll()
            .antMatchers(HttpMethod.GET,"/post","/post/*","/post/**").permitAll()
            .antMatchers(HttpMethod.POST,"/comment","/comment/vote/**").permitAll()
            .antMatchers(HttpMethod.GET,"/comment","/comment/*","/comment/**").permitAll()
            .antMatchers("/image","/image/**").hasRole("ADMIN")
            .antMatchers("admin","admin/*","admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowedOriginPatterns(Arrays.asList("https://*.kayafirat.com"));
        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:8080","http://localhost:4200","*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD","OPTIONS","PUT","PATCH","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Accept","Access-Control-Request-Method","Access-Control-Request-Headers",
                "Accept-Language","Authorization","Content-Type","Request-Name","Request-Surname","Origin","X-Request-AppVersion",
                "X-Request-OsVersion", "X-Request-Device", "X-Requested-With","Access-Control-Allow-Origin"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A,10);
    }

}
