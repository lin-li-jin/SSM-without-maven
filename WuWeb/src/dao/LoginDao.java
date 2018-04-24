package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import util.JdbcUtils;

import domain.User;

public class LoginDao {
	//�����û���
	public int insert(User user){
		Connection connection = null;//���ݿ�����
		PreparedStatement statement = null;//���ݿ�ִ�����
		ResultSet resultSet = null;//���ݿⷵ��ֵ����
		int successNumber = 0;
		try{
			//step 1:��ȡһ����������
			connection = JdbcUtils.getConnection();//�������������util�������JdbcUtils������
			//step 2:Ҫִ�е�sql��䣬����Ҫ����Ĳ�������?����
			String sql = "insert into user(id ,username,password,userType) values(?,?,?,?)";
			//step 3:ͨ��connection�����ȡ����ִ��SQL�����prepareStatement����
			statement =connection.prepareStatement(sql);
			//step 4:Ϊsql����е�?��ֵ��Ĭ���±��1��ʼ
			statement.setString(1, user.getId());//��һ��?��ֵΪ��������user��id
			statement.setString(2, user.getName());//Ϊ�ڶ���?��ֵΪ��������user��name
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getUserType());
			//step 5:ִ�в�����������ص���һ�����֣��������ɹ�������
			successNumber = statement.executeUpdate();	
		}catch (Exception e) {
			e.printStackTrace();//�����׽���쳣���ӡ�쳣
		}finally{
			JdbcUtils.release(connection, statement, resultSet);//���ܳɹ�������Ҫ�ͷ�����
		}
		return successNumber;	
	}
	
	//�����û������û�
	public User findByName(String name){
		Connection connection = null;//���ݿ�����
		PreparedStatement statement = null;//���ݿ�ִ�����
		ResultSet resultSet = null;//���ݿⷵ��ֵ����
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
