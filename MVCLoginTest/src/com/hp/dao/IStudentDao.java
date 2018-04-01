package com.hp.dao;

import com.hp.beans.Student;

public interface IStudentDao {

	Student selectStudentByNum(String num, String password);

	boolean insertDatabase(String num, String password, String name, int age, double score);

}
