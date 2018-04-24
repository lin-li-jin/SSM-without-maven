package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import domain.User;


@WebServlet("/servlet/register")
public class RegisterController extends HttpServlet {	
	LoginDao loginDao = new LoginDao();

	//doGet()方法用来接收用户跳转页面的请求，这里是帮助用户进入登录页面
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.getRequestDispatcher("/WEB-INF/jsp/registerPage.jsp").forward(request, response);
	}
	//当用户点击页面登录的按钮，来这个请求,管理员进入管理员主页，普通用户进入用户主页
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session =request.getSession();
		User user = new User();
		String name = request.getParameter("username");//获取前台传进来的username
		//获取到要注册的用户名，要检查一下，之前有没有用户已经用过这个名字了
		User user2 =loginDao.findByName(name);
		if(user2!=null){//说明检测到了一样的用户名，则不允许用户注册
			session.setAttribute("error_message", "已有用户注册了该用户名");
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
		String password = request.getParameter("password");//获取前台传进来的password
		String userType = request.getParameter("userType");//获取前台传进来的用户类型
		UUID id = UUID.randomUUID();//随机产生一个随机数做ID,这样id不浪费，也不重复
		user.setId(id.toString());
		user.setName(name);
		user.setIsEnable("1");
		user.setPassword(password);
		user.setUserType(userType);
		
		int num = loginDao.insert(user);
		if(num==1){
			request.getRequestDispatcher("/WEB-INF/jsp/loginPage.jsp").forward(request, response);
		}else{
			session.setAttribute("error_message", "用户注册失败");
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
		
		
		
		
		
	}

}
