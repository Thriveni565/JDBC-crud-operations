package com.student.com.student.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCconnectionForStudent {
	public static void main(String[] args) throws SQLException, NullPointerException, Exception {
		Connection connection = null;
		final Student studentObj = new Student();
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "Sneha@123");
		System.out.println("press 1 to add student details");
		System.out.println("press 2 to update student details");
		System.out.println("press 3 to delete student details");
		System.out.println("press 4 to view student details");
		Scanner obj = new Scanner(System.in);
		System.out.println("Enter your choice from above");
		byte choice = obj.nextByte();
		switch(choice) {
		case 1:
			System.out.println("enter how many students you want to insert");
			byte noOfstudentTobeInserted = obj.nextByte();
			for(int ctr = 0; ctr < noOfstudentTobeInserted; ctr++) {
				System.out.println("Enter your name");
				String name = obj.next();
				System.out.println("Enter your subjectName");
				String subjectName = obj.next();
				System.out.println("Enter your marks");
				int marks = obj.nextInt();
				System.out.println("Enter your id");
				int studentId = obj.nextInt();
				studentObj.addStudent(name,subjectName,marks,studentId, connection);
			}
			break;
		case 2:
			System.out.println("enter the id of student to update..");
			byte id = obj.nextByte(); 
			System.out.println("enter the new marks value to update");
			int marks = obj.nextInt();
			studentObj.updateStudentDetails(id,marks,connection);
			break;
		case 3:
			System.out.println("enter the id of student to delete..");
			byte deleteid = obj.nextByte(); 
			studentObj.deleteStudent(deleteid,connection);
			break;
		case 4:
			System.out.println("enter the id of student to view..");
			byte viewId = obj.nextByte(); 
			studentObj.viewStudentDetail(viewId,connection);
			break;
		}
		connection.close();
	}


}
