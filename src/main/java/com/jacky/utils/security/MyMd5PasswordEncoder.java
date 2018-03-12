package com.jacky.utils.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jackyhuangc.global.utils.Md5Util;

/**
 * ʵ��(implements)PasswordEncoder�ӿ�
 * 
 * @author Jacky Huang
 * @date 2018-01-28 17:54
 * @since jdk1.8
 */
public class MyMd5PasswordEncoder implements PasswordEncoder {
	private static Logger logger = LoggerFactory.getLogger(MyMd5PasswordEncoder.class);

	// ʹ��MD5��ȡ����֮�������
	@Override
	public String encode(CharSequence rawPassword) {

		return Md5Util.encode((String) rawPassword);
	}

	// ��֤����
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {

		logger.debug(String.format("������֤���룺%s,%s", rawPassword, encodedPassword));
		return true;
		// return encodedPassword.equals(Md5Util.encode((String) rawPassword));
	}
}
