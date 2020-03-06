package com.xxgc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.xxgc.dao.ArticleDAO;
import com.xxgc.dao.CatalogDAO;
import com.xxgc.model.Catalog;

public class TimeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArticleDAO articleDAO = new ArticleDAO();
		articleDAO.dateToStamp("2019-11-27T03:06:44");
		System.out.println(articleDAO.getNowtime());
		
		
		ArticleDAO articleDAO1 = new ArticleDAO();
		articleDAO1.stampToDate("1574792760000");
		System.out.println(articleDAO1.getRes());
		CatalogDAO catalogDAO = new CatalogDAO();
		ArrayList<Catalog> a=catalogDAO.finacaname("À¸Ä¿");
		System.out.println(a);
	}
}

