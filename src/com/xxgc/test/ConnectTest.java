package com.xxgc.test;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.Connection;

public class ConnectTest {

	public static void main(String[] args) {
		String Driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/blog";
		String username="root";
		String passwd="123456";
		
		//连接数据库
		
		try {
			Class.forName(Driver).newInstance();//加载驱动
			//连接数据库，通过地址，用户名，密码
			Connection coon=DriverManager.getConnection(url, username, passwd);
			System.out.println("数据库连接成功");
			//发送SQL语句
			String sql="delete from manager where managerid=1";
			PreparedStatement ps=coon.prepareStatement(sql);
			//得到结果
			int a=ps.executeUpdate();
			if(a>0){
				System.out.println("删除成功");
			}else{
				System.out.println("删除失败");
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库加载失败");
		}
	}

}
/*String sql="select * from manager where managerid=1";
			PreparedStatement ps=coon.prepareStatement(sql);
			//得到结果
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				System.out.println("managerid:"+rs.getInt(1));
				System.out.println("managername:"+rs.getString(2));
				System.out.println("passwd:"+rs.getString(3));
				System.out.println("mstate:"+rs.getString(4));
			}*/