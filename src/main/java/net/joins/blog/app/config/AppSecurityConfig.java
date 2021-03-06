package net.joins.blog.app.config;

import net.joins.blog.web.config.WebSecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();

        http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
    }

}
