package com.xxgc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelHTMLTag {
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/script>";
	private static final String regEx_html = "<[^>]+>";
	private static final String regEx_space = "\\s*|\t|\r|\n";
	private static final String regEx_w = "<w[^>]*?>[\\s\\S]*?<\\/W[^>]*?";
	private static final String regEx_special = "\\&[a-zA-Z]{1,10};";
	
	
	public static String delHTMLTag(String htmlStr){
		Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
		Matcher m_w = p_w.matcher(htmlStr);
		htmlStr = m_w.replaceAll("");
		
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");
		
		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");
		
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");
		
		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll("");
		
		Pattern p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
		Matcher m_special = p_special.matcher(htmlStr);
		htmlStr = m_special.replaceAll("");
		
		htmlStr = htmlStr.replaceAll(" ", "");
		return htmlStr.trim();
	}
}
