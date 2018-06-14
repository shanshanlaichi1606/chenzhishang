package com.wondersgroup.util;

import java.util.Random;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 提供MD5加密方法
 * 
 * @author wanglei
 */
public class EncodeUtils {
	public static String encodePassword(String password) {
		String encodedPassword = (new Md5PasswordEncoder()).encodePassword(password.trim(), "KU*&*##Fkfksdj00**&&%^@@1--+++73");// 加密
																																// (密码+字符串)
		return encodedPassword;
	}

	/**
	 * 生成随即密码
	 * 
	 * @param pwd_len
	 *            生成的密码的总长度
	 * @return 密码的字符串
	 */
	public static String genRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个 数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止 生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	// 去掉字符串所有括号 ()
	public static String removeBrackets(String str) {
		String newStr = str.replaceAll("\\(|\\)", "");
		return newStr;
	}

	// 去掉所有空格
	public static String removeAllSpace(String str) {
		if (str == null) {
			return null;
		}
		String newStr = str.replaceAll("\\s", "");
		return newStr;
	}
}
