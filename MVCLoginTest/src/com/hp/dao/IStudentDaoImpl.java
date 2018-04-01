package com.hp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hp.beans.Student;
import com.hp.utils.JdbcUtils;

public class IStudentDaoImpl implements IStudentDao {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	@Override
	public Student selectStudentByNum(String num, String password) {
		Student student = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from students where num=? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setNum(rs.getString("num"));
				student.setPassword(rs.getString("password"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setScore(rs.getDouble("score"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtils.close(conn, pstmt, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return student;
	}
	@Override
	public boolean insertDatabase(String num, String password, String name, int age, double score) {
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into students(num,password,name,age,score) VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setInt(4,age);
			pstmt.setDouble(5,score);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtils.close(conn, pstmt, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
