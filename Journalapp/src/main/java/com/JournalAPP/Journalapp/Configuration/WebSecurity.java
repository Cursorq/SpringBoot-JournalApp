package com.JournalAPP.Journalapp.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class WebSecurity {
    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.formLogin(Customizer.withDefaults()).
            authorizeHttpRequests(auth->auth.requestMatchers("/public/**").permitAll()
            .requestMatchers("/admin").hasRole("admin")
            .requestMatchers("/user/**","/journal/**").authenticated()
            .anyRequest().authenticated())
       .httpBasic(Customizer.withDefaults());


    return http.build();
}
//when someone tries to log in:
//
//    Spring Security receives the username.
//
//    It calls UserDetailsService.
//
//    Your implementation fetches the user from DB / memory / API.
//
//    Returns a UserDetails object.
//
//    Spring compares passwords and checks roles.
    @Bean
    public UserDetailsService userDetailsService(){
    UserDetails user= User.withUsername("Joe").password("").roles("user").build();
    return new InMemoryUserDetailsManager(user);
}
}