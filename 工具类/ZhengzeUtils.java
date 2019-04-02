package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhengzeUtils {

	public static void main(String[] args) {

		int i = Integer.MAX_VALUE;
		System.out.println(i);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		boolean matchName = matchName("13655558888");
		System.out.println(matchName);
	}

	public static boolean matchName(String str) {

		// 匹配手机号码的正则表达式
		Pattern pattern = Pattern
				.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
