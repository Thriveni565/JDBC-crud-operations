package com.student.com.student.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
	String name;
	public String getName() {
		return name;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public int getMarks() {
		return marks;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public void setId(int id) {
		this.id = id;
	}

	String subjectName;
	int marks;
	int id;

	void addStudent(String name, String subject, int marks, int id, Connection con) throws SQLException {
		PreparedStatement preparedStmt = con.prepareStatement("insert into student values(?,?,?,?)");
		preparedStmt.setString(1, name);
		preparedStmt.setString(2, subject);
		preparedStmt.setInt(3, marks);
		preparedStmt.setInt(4, id);
		int recordInserted = preparedStmt.executeUpdate();
		System.out.println(recordInserted+ "inserted into db");
	}

	void updateStudentDetails(int id,int marks,Connection connection) throws SQLException {
		PreparedStatement preparedStmt = connection.prepareStatement("update student set marks = ? where id = ?");
		preparedStmt.setInt(1, marks);
		preparedStmt.setInt(2, id);
		int recordInserted = preparedStmt.executeUpdate();
		System.out.println(recordInserted+ " updated into db");
	}

	void deleteStudent(int deleteid,Connection connection) throws SQLException{
		PreparedStatement preparedStmt = connection.prepareStatement("delete from student where id = ?");
		preparedStmt.setInt(1, deleteid);
		int recordInserted = preparedStmt.executeUpdate();
		System.out.println(recordInserted+ " deleted from db");
	}

	void viewStudentDetail(int viewId,Connection connection)throws SQLException {
		PreparedStatement preparedStmt = connection.prepareStatement("select * from student where id = ?");
		preparedStmt.setInt(1, viewId);
		ResultSet resultOfOneStdt = preparedStmt.executeQuery();
		while(resultOfOneStdt.next()) {
			System.out.println(resultOfOneStdt.getString(1)+ " "+resultOfOneStdt.getString(2)+ " "+ resultOfOneStdt.getInt(3) +" "+ resultOfOneStdt.getInt(4));
		}
	}
}
