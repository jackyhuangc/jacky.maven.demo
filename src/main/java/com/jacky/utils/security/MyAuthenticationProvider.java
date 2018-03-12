package com.jacky.utils.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.xujj.springweb.ctrl.GeneralController;

/**
 * DaoAuthenticationProvider��AuthenticationManager��ʵ����
 * 
 * @author Jacky Huang
 * @date 2018-01-28 17:54
 * @since jdk1.8
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider {
	private static Logger logger = LoggerFactory.getLogger(GeneralController.class);
	@Autowired
	private MyUserDetailsService uds;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String requestName = auth.getName();
		String requestPass = auth.getCredentials().toString();
		UserDetails ud = uds.loadUserByUsername(requestName);

		logger.debug("*****************************************************");
		logger.debug(String.format("�������룺%s, ���ݿ����룺%s", requestPass, ud.getPassword()));
		logger.debug("*****************************************************");

		// �Ա��û��������������ݿ������Ƿ�һ��
		if (requestPass.equals(ud.getPassword())) {
			return new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(),
					ud.getAuthorities());
		}
		throw new BadCredentialsException("Bad credentials");
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}
}
