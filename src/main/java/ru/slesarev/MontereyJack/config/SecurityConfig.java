package ru.slesarev.MontereyJack.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Configuration
    @Order(1)
    public class ManagerSecurityConfiguration extends WebSecurityConfigurerAdapter{



        @Value("${manage.login}")
        private String manageLogin;

        @Value("${manage.password}}")
        private String managePassword;

        public void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth
                    .inMemoryAuthentication()
                        .withUser(manageLogin)
                        .password(managePassword)
                    .roles("SYSTEM_MANAGER");

        }

        protected void configure(HttpSecurity http) throws Exception {

            http
                    .antMatcher("/_system/**")
                        .authorizeRequests()
                        .anyRequest()
                    .hasRole("SYSTEM_MANAGER")
                    .and()
                    .httpBasic();

        }

    }

    @Configuration
    @Order(2)
    public static class StreamerSecurityConfiguration extends WebSecurityConfigurerAdapter{

        private final UserDetailService userDetailService;

        public StreamerSecurityConfiguration(UserDetailService userDetailService) {
            this.userDetailService = userDetailService;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                        .antMatchers("/user/**").hasRole("CLIENT")
                        .antMatchers("/admin/**").hasRole("ADMIN")
                    .and()
                        .formLogin()
                        .usernameParameter("username")
                        .loginPage("/login")
                        .defaultSuccessUrl("/auth-success")
                    .and()
                        .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .logoutUrl("/")
                    .and()
                    .csrf().disable();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(userDetailService);
        }
    }

}
