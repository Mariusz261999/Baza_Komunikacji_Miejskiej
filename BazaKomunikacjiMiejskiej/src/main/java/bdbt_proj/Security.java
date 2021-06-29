package bdbt_proj;
// WAZNE ------------------------------------------------------------------------------------------------------------------------------
//https://www.baeldung.com/spring-security-two-login-pages
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class Security extends WebSecurityConfigurerAdapter {
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails normalUser = User.withUsername("user").password("{noop}password").roles("USER").build();

		UserDetails adminUser = User.withUsername("admin").password("{noop}password").roles("ADMIN").build();

		return new InMemoryUserDetailsManager(normalUser, adminUser);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        		.antMatchers("/").authenticated()
        		.antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }
}
