package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDao;
import domain.User;

@WebServlet("/servlet/login")
public class LoginController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	LoginDao loginDao = new LoginDao();

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/loginPage.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = loginDao.findByName(username);
		if(user!=null){
			if(password.equals(user.getPassword())){//判断输入的密码和数据库的密码是否一致
				request.getSession().setAttribute("username", username);
				request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
			}
		}else{//user为null那就是用户没有注册
			request.getSession().setAttribute("error_message", "用户没有注册");
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

}
