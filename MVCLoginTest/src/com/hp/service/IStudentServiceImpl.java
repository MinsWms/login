package com.hp.service;

import com.hp.beans.Student;
import com.hp.dao.IStudentDao;
import com.hp.dao.IStudentDaoImpl;

public class IStudentServiceImpl implements IStudentService {
	private IStudentDao dao;
	
	public IStudentServiceImpl() {
		dao = new IStudentDaoImpl();
	}

	@Override
	public Student checkUser(String num, String password) {
		// TODO Auto-generated method stub
		return dao.selectStudentByNum(num,password);
	}
	@Override
	public boolean insertData(String num, String password, String name, int age, double score) {
		// TODO Auto-generated method stub
		return dao.insertDatabase(num,password,name,age,score);
	}
}
