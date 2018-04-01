package com.hp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hp.beans.Student;
import com.hp.service.IStudentServiceImpl;
import com.hp.service.IStudentService;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//1.获取数据
		String num = request.getParameter("num");
		String password = request.getParameter("password");
		//获取session
		HttpSession session =request.getSession();
		//判断学号输入是否正确
		if(num == null||"".equals(num.trim())) {
			session.setAttribute("message", "学号输入有误！");
			response.sendRedirect(request.getContextPath()+"/loign.jsp");
			return;
		}
		//判断密码输入是否正确
		if(password == null||"".equals(password.trim())) {
			session.setAttribute("message", "密码输入有误！");
			response.sendRedirect(request.getContextPath()+"/loign.jsp");
			return;
		}	
		//2.创建service对象、
		IStudentService service = new IStudentServiceImpl();
		//3.调用service中的checkUser()方法进行验证
		Student student = service.checkUser(num,password);
		//4.处理验证不通过
		if(student==null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			session.setAttribute("message", "学号或密码有误！");
			return;
		}
		//5.处理验证通过
		if(student!=null) {			
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}
	}

}
