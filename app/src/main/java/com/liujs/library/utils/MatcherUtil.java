package com.liujs.library.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtil {
	
	/**
	 * 验证手机号码
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone){
		Pattern p = Pattern   
                .compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[6789012345]|18[0-9]|14[57])[0-9]{8}$");   
        Matcher m = p.matcher(phone);   
        return m.matches();
	}
	
	/**
	 * 验证车牌号码
	 * @param carNo
	 * @return
	 */
	public static boolean isCarNo(String carNo){
		Pattern p = Pattern   
                .compile("^[蒙|冀|黑|宁|云|皖|苏|桂|琼|湘|吉|闽|贵|辽|沪|粤|浙|青|鲁|津|京|藏|甘|川|新|赣|豫|晋|陕|鄂|渝]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5}$");   
        Matcher m = p.matcher(carNo);   
        return m.matches();
	}
	
	/**
	 * 校验邮箱地址
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		 Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		 Matcher matcher = pattern.matcher(email);
		 return matcher.matches();
	}

	/**
	 * 校验密码
	 * @param pwd
	 * @return
	 */
	public static boolean isPassWord(String pwd){
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,16}$");
		Matcher matcher = pattern.matcher(pwd);
		return matcher.matches();
	}

	/**
	 * 功能：判断字符串是否为数字
	 *
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
