package com.glucoclock.security;

//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.glucoclock.views.ErrorPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?")
        ;

    }
    /**
     * Require login to access internal pages and configure login form.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Vaadin handles CSRF internally
        http.csrf().disable()

                // Allow access to some pages for non-logged-in users
                .authorizeRequests().antMatchers("/login","/sign-up","/patient-sign-up-1","/patient-sign-up-2","/patient-sign-up-3","/doctor-sign-up-1","/doctor-sign-up-2","/researcher-sign-up-1","/doctor-sign-up-2","/forgotPassword").permitAll()

                .and().authorizeRequests().antMatchers("/patient/*").hasAuthority("PATIENT")

                .and().authorizeRequests().antMatchers("/doctor/*").hasAuthority("DOCTOR")

                .and().authorizeRequests().antMatchers("/researcher/*").hasAuthority("RESEARCHER")

                .and().exceptionHandling().accessDeniedPage("/error/access-denied")

//                // Register our CustomRequestCache, which saves unauthorized access attempts, so the user is redirected after login.
                .and().requestCache().requestCache(new CustomRequestCache())
//
                // Restrict access to our application.
                .and().authorizeRequests()


               // Allow all Vaadin internal requests.
               .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()

                // Allow all requests by logged-in users.
                .anyRequest().authenticated()

                // Configure the login page.
                .and().formLogin()
                .loginPage(LOGIN_URL).permitAll()
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .failureUrl(LOGIN_FAILURE_URL);

        http.formLogin().defaultSuccessUrl("/", true);



//
//                // Configure logout
//                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }


    //HARD CODING TO BE CHANGED LATER
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        List<UserDetails> users= new ArrayList<>();
//        users.add(User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("userpass")
//                .roles("USER")
//                .build());
//
//        return new InMemoryUserDetailsManager(users);
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Allows access to static resources, bypassing Spring Security.
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                // Client-side JS
                "/VAADIN/**",

                // the standard favicon URI
                "/favicon.ico",

                // the robots exclusion standard
                "/robots.txt",

                // web application manifest
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.html",

                // icons and images
                "/icons/**",
                "/images/**",
                "/styles/**",

//                //sign up page
//                "/views/all/**",

                // (development mode) H2 debugging console
                "/h2-console/**");
    }
}
