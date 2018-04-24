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

	//doGet()�������������û���תҳ������������ǰ����û������¼ҳ��
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.getRequestDispatcher("/WEB-INF/jsp/registerPage.jsp").forward(request, response);
	}
	//���û����ҳ���¼�İ�ť�����������,����Ա�������Ա��ҳ����ͨ�û������û���ҳ
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session =request.getSession();
		User user = new User();
		String name = request.getParameter("username");//��ȡǰ̨��������username
		//��ȡ��Ҫע����û�����Ҫ���һ�£�֮ǰ��û���û��Ѿ��ù����������
		User user2 =loginDao.findByName(name);
		if(user2!=null){//˵����⵽��һ�����û������������û�ע��
			session.setAttribute("error_message", "�����û�ע���˸��û���");
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
		String password = request.getParameter("password");//��ȡǰ̨��������password
		String userType = request.getParameter("userType");//��ȡǰ̨���������û�����
		UUID id = UUID.randomUUID();//�������һ���������ID,����id���˷ѣ�Ҳ���ظ�
		user.setId(id.toString());
		user.setName(name);
		user.setIsEnable("1");
		user.setPassword(password);
		user.setUserType(userType);
		
		int num = loginDao.insert(user);
		if(num==1){
			request.getRequestDispatcher("/WEB-INF/jsp/loginPage.jsp").forward(request, response);
		}else{
			session.setAttribute("error_message", "�û�ע��ʧ��");
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
		
		
		
		
		
	}

}
