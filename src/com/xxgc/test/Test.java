package com.xxgc.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.xxgc.dao.ArticleDAO;
import com.xxgc.model.Article;

public class Test {
	public static void main(String[] args) {
		ArticleDAO articleDAO = new ArticleDAO();
		String artitle = "wo";
		String str=null;
		//char aa='Œ“';
		try {
			str = new String (artitle.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(artitle);
		System.out.println(str);
		ArrayList<Article> a= articleDAO.namepage(1, str);
		System.out.println(a);
		ArrayList<Article> b= articleDAO.namepage(1,"wo");
		System.out.println(b);
	}
	
}
