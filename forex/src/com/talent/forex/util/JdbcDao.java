package com.talent.forex.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDao {
	private  Connection connection;
	
	private JdbcDao(){}
	
	public  JdbcDao(Connection connection1){
		this.connection = connection1;
	}
	

	/**
	 * 在数据库上执行一条sql语句
	 * 无返回结果
	 * */
	public  boolean excuteSQL(String sql){
		boolean isSuccess =false;//操作成功与否标志 
		PreparedStatement ps=null;
		if(getConnection() == null) return false;
		try {
			ps = getConnection().prepareStatement(sql);
			isSuccess= ps.execute();
			isSuccess=true;
		} catch (SQLException e) {
			//如果错误信息为hintMsg的内容只有sessionId变化，则是正常报错，将isSuccess置为true;
			//String hintMsg = "Backup Server session id is:  9.  Use this value when executing the 'sp_volchanged' system stored procedure after fulfilling any volume change request from the Backup Server.";
			String errMsg = e.getMessage();
			if(errMsg.indexOf("Backup Server session id is:") != -1)
				isSuccess = true;
			e.printStackTrace();
		}finally{
			closeCon(getConnection(),ps);
		}
		return isSuccess;
	}
	
	
	public static void closeCon(Connection con,Statement ps){
		try {
			if(ps!=null)ps.close();
			if(con!=null)con.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeCon(Connection con,PreparedStatement ps){
		try {
			if(ps!=null)ps.close();
			if(con!=null)con.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeCon(Connection con,PreparedStatement ps,ResultSet rs){

		try {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(con!=null)con.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
