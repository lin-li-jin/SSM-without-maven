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
	
	static{//这是一个静态初始化块，在类第一次加载的时候初始化，仅初始化一次
		try{
			InputStream in =JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			
			//获取数据库连接驱动
			driver = prop.getProperty("driver");//获取db.properties文件里面的driver属性
			url = prop.getProperty("url");//获取db.properties文件里面的url
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			//加载数据库驱动
			Class.forName(driver);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//获取数据库连接
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
	
	//释放数据库连接
	public static void release(Connection connection ,Statement statement,ResultSet set){
		//关闭存储查询结果的ResultSet对象
		if(set!=null){
			try {
				set.close();
			} catch (Exception e) {
				e.printStackTrace();//如果抛异常则打印出来
			}
			set=null;
		}
		
		//关闭负责执行SQL命令的Statement对象
		if(statement!=null){
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();//如果抛异常则打印出来
			}
		}
				
		//关闭Connection数据库连接对象
		if(connection!=null){
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();//如果抛异常则打印出来
			}
		}
	}
	

}
