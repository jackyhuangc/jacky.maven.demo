/*package com.xujj;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.event.LoggerListener;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

// �˴�������ʹ�ô���ķ�ʽ��webmvc���������÷�ʽ���������׳ɹ�
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

	@Bean // ����bean�Ժ󣬱���Զ�ȡ�����еĶ�Ӧ�ڵ���Ϣ��ʹ��
	public MyAuthenticationProvider myAuthenticationProvider() {
		MyAuthenticationProvider provider = new MyAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub

		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.permitAll();

		// http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(myAuthenticationProvider());
		// user Details Service��֤
		// auth.userDetailsService(userDetailsService()).passwordEncoder(new
		// PasswordEncoder() {
		// // ʹ��MD5��ȡ����֮�������
		// @Override
		// public String encode(CharSequence rawPassword) {
		// return Md5Util.encode((String) rawPassword);
		// }
		//
		// // ��֤����
		// @Override
		// public boolean matches(CharSequence rawPassword, String
		// encodedPassword) {
		// return encodedPassword.equals(Md5Util.encode((String) rawPassword));
		// }
		// });
	}
}*/