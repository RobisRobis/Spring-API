package sp.senai.br.Odonto.securiy;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration

//Adicionar Segurança Web em requisições http
@EnableWebSecurity 

//Adiciona Segurança Global a Aplicação
//Necessário para gerenciamento de autenticação
@EnableGlobalMethodSecurity(securedEnabled = true) 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			
			.anyRequest()
			.authenticated()
			.and().httpBasic()
			.and().csrf().disable();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		
		auth.inMemoryAuthentication()
		.passwordEncoder(enconder)
		.withUser("user")
		.password(enconder.encode("123"))
		.roles("USER")
		.and()
		.withUser("robis")
		.password(enconder.encode("123"))
		.roles("ADMIN")
		.and()
		.withUser("dentista")
		.password(enconder.encode("dentista"))
		.roles("DENTISTA");
	}
}
