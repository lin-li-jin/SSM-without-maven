package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import util.JdbcUtils;

import domain.User;

public class LoginDao {
	//插入用户表
	public int insert(User user){
		Connection connection = null;//数据库连接
		PreparedStatement statement = null;//数据库执行语句
		ResultSet resultSet = null;//数据库返回值对象
		int successNumber = 0;
		try{
			//step 1:获取一个数据连接
			connection = JdbcUtils.getConnection();//这里调用我们在util包下面的JdbcUtils工具类
			//step 2:要执行的sql语句，具体要插入的参数先用?代替
			String sql = "insert into user(id ,username,password,userType) values(?,?,?,?)";
			//step 3:通过connection对象获取负责执行SQL命令的prepareStatement对象
			statement =connection.prepareStatement(sql);
			//step 4:为sql语句中的?赋值，默认下标从1开始
			statement.setString(1, user.getId());//第一个?赋值为传进来的user的id
			statement.setString(2, user.getName());//为第二个?赋值为传进来的user的name
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getUserType());
			//step 5:执行插入操作，返回的是一个数字，代表插入成功的条数
			successNumber = statement.executeUpdate();	
		}catch (Exception e) {
			e.printStackTrace();//如果捕捉到异常则打印异常
		}finally{
			JdbcUtils.release(connection, statement, resultSet);//不管成功与否，最后都要释放连接
		}
		return successNumber;	
	}
	
	//根据用户名找用户
	public User findByName(String name){
		Connection connection = null;//数据库连接
		PreparedStatement statement = null;//数据库执行语句
		ResultSet resultSet = null;//数据库返回值对象
		User user = null;
		try {
			connection = JdbcUtils.getConnection();
			String sql = new String("select * from user where username = ?");
			statement = connection.prepareStatement(sql);
			statement.setString(1,name);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getString("id"));
				user.setName(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setIsEnable(resultSet.getString("isEnable"));
				user.setUserType(resultSet.getString("userType"));
				user.setCreateTime(resultSet.getDate("createTime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(connection, statement, resultSet);
		}
		return user;
	}
	
	
	
}
