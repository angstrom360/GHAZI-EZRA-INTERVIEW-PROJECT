package com.trilogyed.priceCalculation.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
					########################################
					# --- USERS LOGGED IN INFORMATION  --- #
					# To Login: /loggedin                  #
                    # To Logout: /logout                   #
                    #      1. LOG IN AS STAFF:             #
                    #               - UserName: staff      #
                    #               - password: password   #
                    #                                      #
                    #      2. LOG IN AS MANAGER:           #
                    #              - UserName: manager     #
                    #               - password: password   #
                    #                                      #
                    #      3. LOG IN AS ADMIN:             #
                    #              - UserName: admin       #
                    #              - password: password    #
                    #                                      #
					########################################

 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder)throws Exception{

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);


    }

    public void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()

                .mvcMatchers("/loggedin").authenticated()
                /*CREATE COMMENTS & POSTS */
                .mvcMatchers(HttpMethod.POST,"/tax","/product").hasAuthority("ROLE_USER")
                .mvcMatchers(HttpMethod.POST,"/tax","/product").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST,"/tax","/product").hasAuthority("ROLE_ADMIN")
                /*UPDATE COMMENTS & POSTS */
                .mvcMatchers(HttpMethod.PUT,"/tax/{id}","/product/{id}").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.PUT,"/tax/{id}","/product/{id}").hasAuthority("ROLE_ADMIN")
                /*DELETE COMMENTS & POSTS */
                .mvcMatchers(HttpMethod.DELETE,"/tax/{id}","/product/{id}").hasAuthority("ROLE_ADMIN")
                /*ALL OTHER REQUEST*/
                .anyRequest().permitAll();

        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/allDone")
                .deleteCookies("JSESSIONID")
                .deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
