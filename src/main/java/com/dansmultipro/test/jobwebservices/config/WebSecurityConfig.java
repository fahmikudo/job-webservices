package com.dansmultipro.test.jobwebservices.config;

import com.dansmultipro.test.jobwebservices.handler.ForbiddenHandler;
import com.dansmultipro.test.jobwebservices.handler.UnauthorizedHandler;
import com.dansmultipro.test.jobwebservices.security.AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UnauthorizedHandler unauthorizedHandler() {
        return new UnauthorizedHandler();
    }

    @Bean
    public ForbiddenHandler forbiddenHandler() {
        return new ForbiddenHandler();
    }

    @Bean
    public AuthenticationFilter authenticationFilterBean() {
        return new AuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler()).and()
                .exceptionHandling().accessDeniedHandler(forbiddenHandler()).and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()

                // allow auth url
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/v3/api-docs/**", "v3/api-docs/**", "/swagger-ui/**", "swagger-ui/**", "/swagger-ui.html").permitAll()
                .antMatchers("/test/**").permitAll()
                .anyRequest().authenticated();

        // custom JWT based security filter
        httpSecurity.addFilterBefore(authenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();

        return httpSecurity.build();
    }

}
