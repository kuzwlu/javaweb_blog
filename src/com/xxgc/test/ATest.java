package com.xxgc.test;

import java.util.ArrayList;

import com.xxgc.dao.MessageDAO;
import com.xxgc.model.Message;
import com.xxgc.utils.AESUtil;

public class ATest {
	public static void main(String[] args) {
		MessageDAO messageDAO = new MessageDAO();
		ArrayList<Message>  m =messageDAO.findall();
		System.out.println(m);
	}
}
