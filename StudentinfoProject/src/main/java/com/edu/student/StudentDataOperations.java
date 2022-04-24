package com.edu.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class StudentDataOperations {
	static Connection scon=null;
	static ResultSet rs=null;
	static Statement st=null;

	static Scanner sc=new Scanner(System.in);
	static int sid;
	static String sname;
	static String scourse;
	
	public static void insertStudentInfo() {
		//get the connection
		try {
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			System.out.println("My connection"+scon);

			//Input data from user
			System.out.println("Enter id of student");
			sid=sc.nextInt();
			//Check id exists
			String sql="select * from edustudent where sid="+sid;
			rs=st.executeQuery(sql);

			if(!rs.next()) {
				System.out.println("Enter student name");
				sname=sc.next();
				System.out.println("Enter student Course name");
				scourse=sc.next();

				String ins="insert into edustudent values("+sid+",'"+sname+"','"+scourse+"')";
				int  i =st.executeUpdate(ins);
				if(i>0) {
					System.out.println("Student information is registered");
				}
			}else {
				System.out.println("Id already exists Choose another id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  

	}

	public static void selectStudentInfo() {
		//get the Connection
		try
		{
			scon=DbConnect.getConnection();
			st=scon.createStatement();

			String sql="select * from edustudent";
			rs=st.executeQuery(sql);

			System.out.println("ID\tName\tCourseName");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	//based on id
	public static void displayStudentInfo() {
		try {
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			System.out.println("Enter Student_id to display Information");
			sid=sc.nextInt();
			String sql="select * from edustudent where sid="+sid;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}else {
				System.out.println("ID not found");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateStudentInfo() {
		try {
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			System.out.println("Enter Student_id to update information");
			sid=sc.nextInt();
			String sql="select * from edustudent where sid="+sid;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				System.out.println("Enter name to change");
				String name=sc.next(); 
				String upd="update edustudent set sname='"+name+"' where sid="+sid;
				int  i=st.executeUpdate(upd);

				if(i>0) {
					System.out.println("Record is Updated");
				}
			}//if
			else {
				System.out.println("Record not exists");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void deleteStudentInfo() {
		try {
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			System.out.println("Enter Student_id to update information");
			sid=sc.nextInt();
			String sql="select * from edustudent where sid="+sid; 
			rs=st.executeQuery(sql);
			if(rs.next()) {

				String del = "delete from edustudent where sid="+sid;
				int  i=st.executeUpdate(del);

				if(i>0) {
					System.out.println("Record is deleted");
				}
			}
			else {
				System.out.println("Record not exists");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

