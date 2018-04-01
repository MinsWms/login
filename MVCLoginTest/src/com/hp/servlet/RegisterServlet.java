package com.hp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hp.service.IStudentService;
import com.hp.service.IStudentServiceImpl;

public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//1.获取数据
		String num = request.getParameter("num");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		double score = Double.parseDouble(request.getParameter("score"));
		//2.创建service对象
		IStudentService dao = new IStudentServiceImpl();
		//3.调用service对象的insertData()方法
		boolean isOk = false;
		isOk = dao.insertData(num,password,name,age,score);
		//4.注册失败
		HttpSession session = request.getSession();
		if(isOk==false) {
			session.setAttribute("message","注册失败，请检查！");
			response.sendRedirect(request.getContextPath()+"/register.jsp");
			return ;
		}
		//5.注册成功
		if(isOk==true) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			session.setAttribute("message","注册成功，请登录！");
		}
	}

}
