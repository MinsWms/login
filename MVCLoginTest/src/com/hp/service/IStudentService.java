package com.hp.service;

import com.hp.beans.Student;

public interface IStudentService {

	Student checkUser(String num, String password);

	boolean insertData(String num, String password, String name, int age, double score);

}
