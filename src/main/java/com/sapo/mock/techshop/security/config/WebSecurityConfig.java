package com.sapo.mock.techshop.security.config;

import com.sapo.mock.techshop.common.enumtype.ERole;
import com.sapo.mock.techshop.security.handler.RestAccessDeniedHandler;
import com.sapo.mock.techshop.security.handler.RestAuthenticationEntryPoint;
import com.sapo.mock.techshop.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthUserService authUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserService).passwordEncoder(new CustomPasswordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // permitAll()
        final String[] generalEndpoints = {
                "/api/auth/login",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html"
        };

        final String[] managerEndpoints = {
                "/api/dashboard/**"
        };

        final String[] userEndpoints = {
                "/api/user/**"
        };

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(generalEndpoints).permitAll()
//                .antMatchers(HttpMethod.GET, managerEndpoints).permitAll() // priority from upper to lower
                .antMatchers(managerEndpoints).hasAnyAuthority(ERole.ROLE_MANAGER.toString()) //same as hasAnyRole("ROLE_MANAGER")
                .antMatchers(userEndpoints).hasAnyAuthority(ERole.ROLE_USER.toString())
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .addFilterBefore(
                        authenticationJwtTokenFilter(),
                        UsernamePasswordAuthenticationFilter.class)
//                handle exception
                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(new RestAccessDeniedHandler());
    }
}
