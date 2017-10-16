package org.shen.daily.test;

import java.util.regex.Pattern;

import org.junit.Test;
import org.shen.daily.constant.Constant;

public class TestRegex {
	@Test
	public void testEmailRegex() {
		boolean isMatch=Pattern.matches(Constant.EMAIL_REGEX, "1422537078@qq.com");
		System.out.println(isMatch);
		
		isMatch=Pattern.matches(Constant.EMAIL_REGEX, "142qq.com");
		System.out.println(isMatch);
	}
	
	@Test
	public void testTelRegex() {
		boolean isMatch=Pattern.matches(Constant.TEL_REGEX, "18581517585");
		System.out.println(isMatch);
		
		isMatch=Pattern.matches(Constant.TEL_REGEX, "12345678911");
		System.out.println(isMatch);
	}
	
	@Test
	public void testIdRegex() {
		boolean isMatch=Pattern.matches(Constant.ID_REGEX, "2016221001020");
		System.out.println(isMatch);
		
		isMatch=Pattern.matches(Constant.TEL_REGEX, "20162203000000123asd");
		System.out.println(isMatch);
	}
	
}
