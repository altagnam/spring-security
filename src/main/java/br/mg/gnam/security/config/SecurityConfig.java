package br.mg.gnam.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.mg.gnam.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Service utilizado para autenticar o usuário com o login e senha
	 * informados de acordo com as informações salvas no banco de dados.
	 */
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	
	/**
	 * Bean responsável pela criptografia da senha
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/h2", "/h2/*", "h2/login.do").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/api/*").permitAll()
				.antMatchers("/index.html").authenticated().and()
				.formLogin().permitAll()
				.defaultSuccessUrl("/index.html").and().logout().permitAll().and()
				.logout().permitAll();
		
		
		http.headers().frameOptions().disable();
	}
}
