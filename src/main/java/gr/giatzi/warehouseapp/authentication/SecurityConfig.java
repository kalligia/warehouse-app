package gr.giatzi.warehouseapp.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "index.html").permitAll()
//                        .requestMatchers( "login.html").permitAll()
//                        .requestMatchers("/warehouse/products/**").permitAll()
//                        .requestMatchers("/warehouse/employees").permitAll()
//                        .requestMatchers("/warehouse/employees/add").hasAnyAuthority(Role.MANAGER.name())
//                        .requestMatchers("/warehouse/employees/edit/**").hasAnyAuthority(Role.MANAGER.name())
//                        .requestMatchers("/warehouse/employees/delete/**").hasAnyAuthority(Role.MANAGER.name())
//                        .requestMatchers("/warehouse/users/register").permitAll()
//                        .requestMatchers("/css/**").permitAll()
//                        .requestMatchers("/img/**").permitAll()
//                        .requestMatchers("/uploads/**").permitAll()
//                        .anyRequest().authenticated()
                               .anyRequest().permitAll()

              )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        //.permitAll()
                        .defaultSuccessUrl("/")
                )
                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }
}
