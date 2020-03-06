package com.xxgc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.xxgc.model.*;
import com.xxgc.utils.JDBCUtils;

public class ManagerDAO {

	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	public int pagesize=5;//一页显示的条数

	public ManagerDAO(){
		conn=new JDBCUtils().conn;
		
	}
	
	
	public int getPagesize() {
		return pagesize;
	}


	public int setPagesize(int pagesize) {
		return this.pagesize = pagesize;
	}


	/**
	 * 
	 * @param 连接数据库
	 * @return
	 */
	
	public Manager User(Manager manager){
		//连接数据库
		//实例化users
		//编写sql语句
		String sql="select * from manager where managername='"+manager.getManagername()+"'and passwd='"+manager.getPasswd()+"'";
		Manager newman=new Manager();
		//预加载sql
		try {
			ps=conn.prepareStatement(sql);
			//得到结果
			 rs=ps.executeQuery();
			//处理结果
			if(rs.next()){
				newman.setManagerid(rs.getInt(1));
				newman.setManagername(rs.getString(2));
				newman.setPasswd(rs.getString(3));
				newman.setMatate(rs.getString(4));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newman;
	}
	
	public Manager findname(String username){
		Manager manager=new Manager();
		String sql="select * from manager where managername like '"+username+"'";
		try {
			ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			if(rs.next()){
				manager.setManagerid(rs.getInt(1));
				manager.setManagername(rs.getString(2));
				manager.setPasswd(rs.getString(3));
				manager.setMatate(rs.getString(4));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manager;
	}
	public ArrayList<Manager> finaname(String username){
		String sql="select * from manager where managername like '%"+username+"%'";
		ArrayList<Manager> managerList=new ArrayList<Manager>();
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Manager manager=new Manager();
				manager.setManagerid(rs.getInt(1));
				manager.setManagername(rs.getString(2));
				manager.setPasswd(rs.getString(3));
				manager.setMatate(rs.getString(4));
				managerList.add(manager);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return managerList;
	}
	
	//查询未删除管理员
	public ArrayList<Manager> finaAll(){
		String sql="select * from manager where matate='1' or matate='2'";
		ArrayList<Manager> managerList=new ArrayList<Manager>();
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Manager manager=new Manager();
				manager.setManagerid(rs.getInt(1));
				manager.setManagername(rs.getString(2));
				manager.setPasswd(rs.getString(3));
				manager.setMatate(rs.getString(4));
				managerList.add(manager);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return managerList;
	}

	
	//查询删除的管理员
	public ArrayList<Manager> recycled(){
		String sql="select * from manager where matate=3";
		ArrayList<Manager> managerList=new ArrayList<Manager>();
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Manager manager=new Manager();
				manager.setManagerid(rs.getInt(1));
				manager.setManagername(rs.getString(2));
				manager.setPasswd(rs.getString(3));
				manager.setMatate(rs.getString(4));
				managerList.add(manager);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return managerList;
	}
	
	//添加管理员
	public boolean insert(Manager manager){

		String sql="insert into manager(managername,passwd,matate) values('"+
				manager.getManagername()+"','"+
				manager.getPasswd()+"','"+
				manager.getMatate()+"')";
		try {
			ps = conn.prepareStatement(sql);
			int a=ps.executeUpdate();
			if(a>0){
				ps.close();
				return true;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean update(Manager manager){
		//把date转换成正常格式
		String sql="update manager set passwd='"+manager.getPasswd()+"',matate='"+manager.getMatate()+"' where managerid="+manager.getManagerid();
		try {
			ps = conn.prepareStatement(sql);
			int a=ps.executeUpdate();
			if(a>0){
				return true;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//伪删除
	public boolean recycled(Manager manager){
		//把date转换成正常格式
		String sql="update manager set matate='3' where managerid="+manager.getManagerid();
		try {
			ps = conn.prepareStatement(sql);
			int a=ps.executeUpdate();
			if(a>0){
				ps.close();
				return true;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//恢复
	public boolean recovery(Manager manager){
		//把date转换成正常格式
		String sql="update manager set matate='"+manager.getMatate()+"' where managerid='"+manager.getManagerid()+"'";
		try {
			ps = conn.prepareStatement(sql);
			int a=ps.executeUpdate();
			if(a>0){
				ps.close();
				return true;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(Manager manager){
		String sql="delete from manager where managerid="+manager.getManagerid();
		try {
			ps = conn.prepareStatement(sql);
			int a=ps.executeUpdate();
			if(a>0){
				return true;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Manager findbyid(int id){
		Manager manager=new Manager();
		
		String sql="select * from manager where managerid="+id;
		try {
			ps=conn.prepareStatement(sql);

			 rs=ps.executeQuery();
			if(rs.next()){
				manager.setManagerid(rs.getInt(1));
				manager.setManagername(rs.getString(2));
				manager.setPasswd(rs.getString(3));
				manager.setMatate(rs.getString(4));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manager;
		
	}
	
	/**
	 * 
	 * @param 管理员列表分页
	 * @param 
	 * @return ArrayList<Manager>
	 */
	
	
	public ArrayList<Manager> page(int startpage){
		String sql="select * from manager where matate='1' or matate='2' limit "+(startpage-1)*pagesize+","+pagesize+" ";
		ArrayList<Manager> managerList=new ArrayList<Manager>();
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Manager manager=new Manager();
				manager.setManagerid(rs.getInt(1));
				manager.setManagername(rs.getString(2));
				manager.setPasswd(rs.getString(3));
				manager.setMatate(rs.getString(4));
				managerList.add(manager);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return managerList;
	}	
	
	/**
	 * 
	 * @param 回收站列表分页
	 * @param pagesize
	 * @return
	 */
	public ArrayList<Manager> Recpage(int startpage){
		String sql="select * from manager where matate='3' limit "+(startpage-1)*pagesize+","+pagesize+" ";
		ArrayList<Manager> managerList=new ArrayList<Manager>();
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Manager manager=new Manager();
				manager.setManagerid(rs.getInt(1));
				manager.setManagername(rs.getString(2));
				manager.setPasswd(rs.getString(3));
				manager.setMatate(rs.getString(4));
				managerList.add(manager);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return managerList;
	}	
	//ip
	public String getRequestRealIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.contains(",")) {
			ip = ip.split(",")[0];
		}

		if (!checkIp(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (!checkIp(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!checkIp(ip)) {
			ip = request.getHeader("X-Real-IP");
		}

		if (!checkIp(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	private static boolean checkIp(String ip) {
		if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip) ) {
			return false;
		}
		return true;
	}
	
	//添加头像
	public boolean maimage(String maimage,String managername){
		String sql="update manager set maimage='"+maimage+"' where managername='"+managername+"'";
		try {
			ps = conn.prepareStatement(sql);
			int a=ps.executeUpdate();
			if(a>0){
				return true;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public String getimage(String managername){
		String sql="select maimage from manager where managername='"+managername+"'";
		Manager manager = new Manager();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				manager.setMaimage(rs.getString(1));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manager.getMaimage();
		
	}
	

	
}
