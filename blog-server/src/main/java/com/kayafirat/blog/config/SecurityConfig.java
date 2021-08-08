package com.kayafirat.blog.config;

import com.kayafirat.blog.security.JwtRequestFilter;
import com.kayafirat.blog.service.impl.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*List<Security> securities = securityService.getLinks();
        for (Security security:securities) {
            String[] roles = new String[security.getRole().size()];
            int i = 0;
            for (Role role:security.getRole()) {
                roles[i] = role.getRole().substring(role.getRole().indexOf("_")+1);
                i++;
            }
            if(!security.isPermitAll())
                http.authorizeRequests().antMatchers(security.getHttpMethod(),security.getLink()).hasAnyRole(roles);
            else
                http.authorizeRequests().antMatchers(security.getHttpMethod(),security.getLink()).permitAll();
        }*/
        http
            .csrf().disable()
            .cors().configurationSource(corsConfigurationSource())
            .and()
            .authorizeRequests()
            .antMatchers("/user/current").authenticated()
            .anyRequest().permitAll()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
       // configuration.setAllowedOrigins(Arrays.asList("https://blog.kayafirat.com","https://admin.kayafirat.com"));
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
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
