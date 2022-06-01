package com.ineri.ineri_lk.configuration;

import com.ineri.ineri_lk.service.impl.UserService;
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
    private UserService userService;

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/scripts/**")
                .antMatchers("/styles/**")
                .antMatchers("/images/**")
                .antMatchers("/font/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/signup").not().fullyAuthenticated()
                    .antMatchers("/", "/contacts", "/vacancies/**", "/catalog/**").permitAll()
                    .antMatchers("/users/**").hasRole("ADMIN")
                    .antMatchers("/estate-objects/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/contacts")
                    .failureUrl("/login?error")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/catalog");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("user"))
                .roles("USER");
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .roles("USER", "ADMIN");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .usersByUsernameQuery("select username, password, active from usr where username=?")
//                .authoritiesByUsernameQuery("select u.username, ur.roles from users u inner join user_role ur on u.id = ur.user_id where u.username=?");
//    }


}