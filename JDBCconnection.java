package com.student.com.student.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class JDBCconnection {
	static final Scanner Obj = new Scanner(System.in);
	public static void createStudentData(Connection connection ,StudentBluePrint stdObj) throws SQLException {

		PreparedStatement pstmt = connection.prepareStatement("insert into student values(?,?,?,?,?)");

		pstmt.setInt(1, stdObj.getRollNo());
		pstmt.setString(2, stdObj.getStudentName());
		pstmt.setString(3, stdObj.getFavSub());
		pstmt.setInt(4, stdObj.getTotalMarks());
		pstmt.setInt(5, stdObj.getMoblileNo());
		int records = pstmt.executeUpdate();
		System.out.println(records + " inserted to student table succesfully");

	}	
	private static void viewStudentData(Connection connection)throws SQLException {
		System.out.println("Enter the rollNo to View ");
		int rollnum=Obj.nextInt();
		PreparedStatement preparedStmt = connection.prepareStatement("select * from student where rollNo = ?");
		preparedStmt.setInt(1, rollnum);
		ResultSet result = preparedStmt.executeQuery();
		while(result.next()) {
			System.out.println(result.getInt(1)+ " "+result.getString(2)+ " "+ result.getString(3) +" "+ result.getInt(4)+" "+ result.getInt(5));
		}
	}
	private  static void updateStudentData(Connection connection)throws SQLException {
		System.out.println("Enter the rollNo to update ");
		int rollnum=Obj.nextInt();
		System.out.println("Enter the studentName to update ");
		String name=Obj.nextLine();
		Obj.nextLine();
		System.out.println("Enter the FavSub to update ");
		String sub=Obj.nextLine();
		System.out.println("Enter the TotalMarks to update ");
		int marks=Obj.nextInt();
		System.out.println("Enter the MoblileNo to update ");
		int mobilenum=Obj.nextInt();
		
		PreparedStatement preparedStmt = connection.prepareStatement("	update student set studentName=?,favSub=?,totalMarks=?,moblileNo=? where rollNo=?");
		preparedStmt.setString(1, name);
		
		preparedStmt.setString(2, sub);
		preparedStmt.setInt(3, marks);
		preparedStmt.setInt(4, mobilenum);
		preparedStmt.setInt(5, rollnum);
				
		int records=preparedStmt.executeUpdate();
		System.out.println(records + "record updated succesfully");
	}

	private static void deleteStudent(Connection connection)throws SQLException {

		System.out.println("Enter the RollNo to delete from Student Table");
		int roolno=Obj.nextInt();
		PreparedStatement pstmt=connection.prepareStatement("delete from student where rollNo=? ");
		pstmt.setInt(1, roolno);

		int records=pstmt.executeUpdate();
		System.out.println(records + " record deleted succesfully");
	}
	

	public static void main(String []args)throws Exception {
		Connection connection = null;
		//final AppForBank bankObj = new AppForBank();
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "Sneha@123");
		System.out.println("click 1 to Create student data");
		System.out.println("click 2 to Read Student data");
		System.out.println("click 3 to Update student data");
		System.out.println("click 4 to Delete student data");

		System.out.println("Please Choose Your option");

		Scanner obj=new Scanner(System.in);
		byte choice= obj.nextByte();
		switch(choice) {
		case 1:
			StudentBluePrint obj1=getStudent();
			createStudentData( connection , obj1);
			break;

		case 2:
		
			viewStudentData( connection);

			break;	

		case 3:
			
			updateStudentData( connection);
			break;	

		case 4:
			deleteStudent( connection);
			break;	


		default:
			System.out.println("Enter valied input");	
			connection.close();
		}

	}

	private static  StudentBluePrint getStudent() {
		StudentBluePrint stdnt=new StudentBluePrint();
		System.out.println("Enter Your Roll Number");
		stdnt.setRollNo(Obj.nextInt());

		System.out.println("Enter Your Name");
		stdnt.setStudentName(Obj.next());

		System.out.println("Enter Your Fav Subject");
		stdnt.setFavSub(Obj.next());

		System.out.println("Enter Your total marks");
		stdnt.setTotalMarks(Obj.nextInt());

		System.out.println("Enter Your Mobile Number");
		stdnt.setMoblileNo(Obj.nextInt());

		
		return stdnt;
	}

}
