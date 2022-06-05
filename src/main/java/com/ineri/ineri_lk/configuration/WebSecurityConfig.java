package com.ineri.ineri_lk.configuration;

import com.ineri.ineri_lk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author Kozlov Alexander
 * .usernameParameter("username")
 * .passwordParameter("password")
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/scripts/**")
                .antMatchers("/styles/**")
                .antMatchers("/images/**")
                .antMatchers("/font/**")
                .antMatchers("/upload/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/signup").not().fullyAuthenticated()
                    .antMatchers("/", "/contacts", "/vacancies/**", "/catalog/**", "/cookie", "/error").permitAll()
                    .antMatchers("/**/profile/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/users/**").hasRole("ADMIN")
                    .antMatchers("/estate-objects/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/catalog")
                    .failureUrl("/login?error")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/catalog");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


}