package com.xxgc.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtils {
	String Driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/blog?characterEncoding=utf8";
	String username="root";
	String passwd="123456";
	
	public Connection conn=null;
	public JDBCUtils(){
		try {
			Class.forName(Driver).newInstance();
			conn=DriverManager.getConnection(url, username, passwd);
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
	public static void close(Connection con,ResultSet rs,PreparedStatement ps)throws Exception{
		if(ps!=null){ps.close();}
		if(rs!=null){ps.close();}
		if(con!=null){ps.close();}
	}
}
