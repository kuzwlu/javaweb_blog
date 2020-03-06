package com.xxgc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.xxgc.model.Article;
import com.xxgc.model.Manager;
import com.xxgc.model.Message;
import com.xxgc.model.MessageReplay;
import com.xxgc.model.User;
import com.xxgc.utils.JDBCUtils;

public class MessageDAO {
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	public int pagesize=5;//一页显示的条数

	public MessageDAO(){
		conn=new JDBCUtils().conn;
		
	}
	
	
	public int getPagesize() {
		return pagesize;
	}


	public int setPagesize(int pagesize) {
		return this.pagesize = pagesize;
	}
	
	public ArrayList<Message> findall(){
		String sql="select * from message";
		ArrayList<Message> messagelist = new ArrayList<Message>();
		try {
			ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			while(rs.next()){
				Message message = new Message();
				message.setMeid(rs.getInt(1));
				message.setArid(rs.getInt(2));
				message.setMetitle(rs.getString(3));
				message.setUserid(rs.getInt(4));
				message.setManagerid(rs.getInt(5));
				message.setMecontent(rs.getString(6));
				message.setMetime(rs.getString(7));
				message.setMestate(rs.getString(8));
				messagelist.add(message);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messagelist;
	}
	
	public ArrayList<Message> findbypage(int startpage){
		String sql="select * from message order by meid desc limit "+(startpage-1)*pagesize+","+pagesize+" ";
		ArrayList<Message> messagelist = new ArrayList<Message>();
		try {
			ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			while(rs.next()){
				Message message = new Message();
				message.setMeid(rs.getInt(1));
				message.setArid(rs.getInt(2));
				message.setMetitle(rs.getString(3));
				message.setUserid(rs.getInt(4));
				message.setManagerid(rs.getInt(5));
				message.setMecontent(rs.getString(6));
				message.setMetime(rs.getString(7));
				message.setMestate(rs.getString(8));
				messagelist.add(message);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messagelist;
	}
	
	public String artitle(int arid){
		String sql="select artitle from article where arid="+arid;
		Article article =new  Article();
		try {
			ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			if(rs.next()){
				article.setArtitle(rs.getString(1));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article.getArtitle();
	}
	
	public String managername(int id){
		String sql="select managername from manager where managerid="+id;
		Manager manager = new Manager();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				manager.setManagername(rs.getString(1));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manager.getManagername();
		
	}
	public String username(int id){
		String sql="select username from user where userid="+id;
		User user = new User();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				user.setUsername(rs.getString(1));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user.getUsername();
		
	}
	public Message findbyid(int id){
		Message message=new Message();
		String sql="select * from message where meid ="+id;
		try {
			ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			if(rs.next()){
				message.setMeid(rs.getInt(1));
				message.setArid(rs.getInt(2));
				message.setMetitle(rs.getString(3));
				message.setUserid(rs.getInt(4));
				message.setManagerid(rs.getInt(5));
				message.setMecontent(rs.getString(6));
				message.setMetime(rs.getString(7));
				message.setMestate(rs.getString(8));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	public boolean del(int id){
		String sql="DELETE from message WHERE meid="+id;
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
	public boolean update(int id,String mestate){
		String sql="update message set mestate='"+mestate+"' where meid="+id;
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
	public ArrayList<MessageReplay> findbymeid(int meid){
		String sql="select * from mereplay where meid= "+meid;
		ArrayList<MessageReplay> messageReplayList = new ArrayList<MessageReplay>();
		try {
			ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			while(rs.next()){
				MessageReplay messageReplay = new MessageReplay();
				messageReplay.setMrid(rs.getInt(1));
				messageReplay.setMeid(rs.getInt(2));
				messageReplay.setMrmanager(rs.getInt(3));
				messageReplay.setMruser(rs.getInt(4));
				messageReplay.setMrcontent(rs.getString(5));
				messageReplay.setMrtime(rs.getString(6));
				messageReplayList.add(messageReplay);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageReplayList;
	}
	
	
	
	public boolean replay(MessageReplay messageReplay){
		String sql="INSERT into mereplay(meid,mrmanager,mruser,mrcontent,mrtime) VALUES("+messageReplay.getMeid()+","+messageReplay.getMrmanager()+","+messageReplay.getMruser()+",'"+messageReplay.getMrcontent()+"','"+messageReplay.getMrtime()+"')";
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
	public int findbyname(String name){
		String sql="select managerid from manager where managername='"+name+"'";
		Manager manager = new Manager();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				manager.setManagerid(rs.getInt(1));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manager.getManagerid();
	}
	public boolean delreplay(int mrid){
		String sql="DELETE from mereplay WHERE mrid="+mrid;
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
	public ArrayList<Message> findbyarid(int arid){
		String sql="select * from message where arid ="+arid;
		ArrayList<Message> messagelist = new ArrayList<Message>();
		try {
			ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			while(rs.next()){
				Message message = new Message();
				message.setMeid(rs.getInt(1));
				message.setArid(rs.getInt(2));
				message.setMetitle(rs.getString(3));
				message.setUserid(rs.getInt(4));
				message.setManagerid(rs.getInt(5));
				message.setMecontent(rs.getString(6));
				message.setMetime(rs.getString(7));
				message.setMestate(rs.getString(8));
				messagelist.add(message);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messagelist;
	}
	public ArrayList<Message> findbyarid(int arid,String mestate){
		String sql="select * from message where mestate='"+mestate+"' and arid ="+arid;
		ArrayList<Message> messagelist = new ArrayList<Message>();
		try {
			ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			while(rs.next()){
				Message message = new Message();
				message.setMeid(rs.getInt(1));
				message.setArid(rs.getInt(2));
				message.setMetitle(rs.getString(3));
				message.setUserid(rs.getInt(4));
				message.setManagerid(rs.getInt(5));
				message.setMecontent(rs.getString(6));
				message.setMetime(rs.getString(7));
				message.setMestate(rs.getString(8));
				messagelist.add(message);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messagelist;
	}
	
	public ArrayList<MessageReplay> findbymrid(int mrid){
		String sql="select * from mereplay where mrid= "+mrid;
		ArrayList<MessageReplay> messageReplayList = new ArrayList<MessageReplay>();
		try {
			ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			while(rs.next()){
				MessageReplay messageReplay = new MessageReplay();
				messageReplay.setMrid(rs.getInt(1));
				messageReplay.setMeid(rs.getInt(2));
				messageReplay.setMrmanager(rs.getInt(3));
				messageReplay.setMruser(rs.getInt(4));
				messageReplay.setMrcontent(rs.getString(5));
				messageReplay.setMrtime(rs.getString(6));
				messageReplayList.add(messageReplay);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageReplayList;
	}
	
	public boolean add(Message message){
		String sql="INSERT INTO message (arid,metitle,userid,managerid,mecontent,metime,mestate) VALUES ("+message.getArid()+",'"+message.getMetitle()+"','"+message.getUserid()+"',"+message.getManagerid()+",'"+message.getMecontent()+"','"+message.getMetime()+"','"+message.getMestate()+"')";
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
}
