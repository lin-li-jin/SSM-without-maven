package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	static{//����һ����̬��ʼ���飬�����һ�μ��ص�ʱ���ʼ��������ʼ��һ��
		try{
			InputStream in =JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			
			//��ȡ���ݿ���������
			driver = prop.getProperty("driver");//��ȡdb.properties�ļ������driver����
			url = prop.getProperty("url");//��ȡdb.properties�ļ������url
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			//�������ݿ�����
			Class.forName(driver);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//��ȡ���ݿ�����
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
	
	//�ͷ����ݿ�����
	public static void release(Connection connection ,Statement statement,ResultSet set){
		//�رմ洢��ѯ�����ResultSet����
		if(set!=null){
			try {
				set.close();
			} catch (Exception e) {
				e.printStackTrace();//������쳣���ӡ����
			}
			set=null;
		}
		
		//�رո���ִ��SQL�����Statement����
		if(statement!=null){
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();//������쳣���ӡ����
			}
		}
				
		//�ر�Connection���ݿ����Ӷ���
		if(connection!=null){
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();//������쳣���ӡ����
			}
		}
	}
	

}
