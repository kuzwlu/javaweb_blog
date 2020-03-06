package com.xxgc.dao;

import com.xxgc.model.Catalog;
import com.xxgc.model.Count;
import com.xxgc.model.Manager;
import com.xxgc.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CatalogDAO {
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	public int pagesize=5;//一页显示的条数

	
	public int getPagesize() {
		return pagesize;
	}


	public int setPagesize(int pagesize) {
		return this.pagesize = pagesize;
	}

	public CatalogDAO(){
		conn=new JDBCUtils().conn;
		
	}
	
	
	
	public ArrayList<Catalog> finabystate(String castate){
		String sql="select * from catalog where castate='"+castate+"'";
		ArrayList<Catalog> CatalogList=new ArrayList<Catalog>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Catalog catalog=new Catalog();
				catalog.setCaid(rs.getInt(1));
				catalog.setCaname(rs.getString(2));
				catalog.setCanumber(rs.getString(3));
				catalog.setCastate(rs.getString(4));
				CatalogList.add(catalog);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CatalogList;
	}
	/**
	 * 全查栏目
	 * @return 
	 */
	public ArrayList<Catalog> finaAll(){
		String sql="select * from catalog";
		ArrayList<Catalog> CatalogList=new ArrayList<Catalog>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Catalog catalog=new Catalog();
				catalog.setCaid(rs.getInt(1));
				catalog.setCaname(rs.getString(2));
				catalog.setCanumber(rs.getString(3));
				catalog.setCastate(rs.getString(4));
				CatalogList.add(catalog);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CatalogList;
	}
	/**
	 * 
	 * @param 分页查询
	 * @return
	 */
	
	public ArrayList<Catalog> page(int startpage){
		String sql="select * from catalog order by canumber desc limit "+(startpage-1)*pagesize+","+pagesize+"";
		ArrayList<Catalog> CatalogList=new ArrayList<Catalog>();
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Catalog catalog=new Catalog();
				catalog.setCaid(rs.getInt(1));
				catalog.setCaname(rs.getString(2));
				catalog.setCanumber(rs.getString(3));
				catalog.setCastate(rs.getString(4));
				CatalogList.add(catalog);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CatalogList;
	}	
	/**
	 * 
	 * @param 添加栏目
	 * @return
	 */
	public boolean insert(Catalog catalog){

		String sql="insert into catalog(caname,canumber,castate) values('"+
				catalog.getCaname()+"','"+
				catalog.getCanumber()+"','"+
				catalog.getCastate()+"')";
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
	/**
	 * 
	 * @param 查询总数
	 * @return
	 */
	public ArrayList<Count> count(){
		String sql="select MAX(CAST(canumber as decimal)) from catalog";
		ArrayList<Count> countList=new ArrayList<Count>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Count count=new Count();
				count.setCounts(rs.getInt(1));
				countList.add(count);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countList;
	}
	/**
	 * 
	 * @param 删除
	 * @return
	 */
	public boolean delete(Catalog catalog){
		String sql="delete from catalog where caid="+catalog.getCaid();
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
	/**
	 * 删除更新
	 */
	public boolean deleteupdate(int canumber){
		String sql="update catalog set canumber =canumber-1 where canumber>"+canumber;
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
	
	
	/**
	 * findbycaid
	 */
	public ArrayList<Catalog> finabycaid(int caid){
		String sql="select * from catalog where caid="+caid;
		ArrayList<Catalog> CatalogList=new ArrayList<Catalog>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Catalog catalog=new Catalog();
				catalog.setCaid(rs.getInt(1));
				catalog.setCaname(rs.getString(2));
				catalog.setCanumber(rs.getString(3));
				catalog.setCastate(rs.getString(4));
				CatalogList.add(catalog);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CatalogList;
	}
	
	/**
	 * 
	 * @param 更新
	 * @return
	 */
	public boolean update(Catalog catalog){
		String sql="update catalog set caname='"+catalog.getCaname()+"',castate='"+catalog.getCastate()+"' where caid="+catalog.getCaid();
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
	
	/**
	 * 上移
	 */
	public boolean catalogup(String canumber, String canumberup){

		String sql="update catalog AS a JOIN catalog AS b ON(a.canumber ='"+canumber+"' AND b.canumber='"+canumberup+"') OR (a.canumber='"+canumberup+"'  AND b.canumber ='"+canumber+"')set b.canumber=a.canumber";
		try {
			ps = conn.prepareStatement(sql);
			int a=ps.executeUpdate();
			if(a>0&a<2){
				return true;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public String prenumber(String canumber){

		String sql="select canumber from catalog where (canumber+0)>'"+canumber+"' "+
					"order by canumber+0 asc limit 1";
		try {
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	//搜索栏目名
	public ArrayList<Catalog> finacaname(String caname){
		String sql="select * from catalog where caname like '%"+caname+"%'";
		ArrayList<Catalog> CatalogList=new ArrayList<Catalog>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Catalog catalog=new Catalog();
				catalog.setCaid(rs.getInt(1));
				catalog.setCaname(rs.getString(2));
				catalog.setCanumber(rs.getString(3));
				catalog.setCastate(rs.getString(4));
				CatalogList.add(catalog);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CatalogList;
	}
	
}
