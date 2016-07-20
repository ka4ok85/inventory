package com.example.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.zookeeper.server.auth.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.JwtAuthenticationEntryPoint;
import com.example.security.JwtAuthenticationTokenFilter;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    //@Autowired
	//private AuthenticationDetailsSource<HttpServletRequest, ?> webAuthenticationDetailsSourceImpl;

    @Autowired
	private WebAuthenticationDetailsSourceImpl webAuthenticationDetailsSourceImpl;
    
    @Autowired
    private CustomUserDetailsAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }    

    @Bean
    public CustomUserDetailsAuthenticationProvider myAuthProvider() throws Exception {
    	CustomUserDetailsAuthenticationProvider provider = new CustomUserDetailsAuthenticationProvider();
    	provider.setPasswordEncoder(passwordEncoder());
    	provider.setUserDetailsService(userDetailsService);

    	return provider; 
    }

    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
    	UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter  = new UsernamePasswordAuthenticationFilter();
    	usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
    	usernamePasswordAuthenticationFilter.setAuthenticationDetailsSource(webAuthenticationDetailsSourceImpl);
		return usernamePasswordAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public UsernamePasswordStoreAuthenticationFilter authenticationTokenFilterBean2() throws Exception {
        UsernamePasswordStoreAuthenticationFilter authenticationTokenFilter = new UsernamePasswordStoreAuthenticationFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        authenticationTokenFilter.setAuthenticationDetailsSource(webAuthenticationDetailsSourceImpl);
        return authenticationTokenFilter;
    }
    
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        authenticationTokenFilter.setAuthenticationDetailsSource(webAuthenticationDetailsSourceImpl);

        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // allow anonymous resource requests
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/api/getstores/short"
                ).permitAll()

                .antMatchers("/auth/**").permitAll()

                              

                
                .anyRequest().authenticated()
                .and()  
                .formLogin()
                  //.loginPage("/login")
                  //.loginProcessingUrl("/login-processing-url")
                  //.usernameParameter("j_username")
                  //.passwordParameter("j_password")
                  .authenticationDetailsSource(webAuthenticationDetailsSourceImpl)
                  //.defaultSuccessUrl("/welcome")
                  .permitAll()
                ;

        // Custom JWT based security filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
                //.addFilterBefore(authenticationTokenFilterBean2(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.headers().cacheControl();
    }
}
