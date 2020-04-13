package by.epamlab.config;

import by.epamlab.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/main*").hasAnyRole("ADMIN", "USER")
                .and()
                    .authorizeRequests().antMatchers("/admin*", "/addProduct*").hasRole("ADMIN")
                .and()
                    .authorizeRequests().antMatchers("/login", "/home", "/registration", "/resources/**").permitAll()
                .and()
                    .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
                    .loginProcessingUrl("/doLogin")
                    .successForwardUrl("/postLogin")
                    .failureUrl("/loginFailed")
                .and()
                    .logout().logoutUrl("doLogout").logoutSuccessUrl("/login").permitAll()
                .and()
                    .sessionManagement()
                        .invalidSessionUrl("/login")
                        .maximumSessions(1)
                        .expiredUrl("/login");



    }

}