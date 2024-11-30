package com.fileexplorer.connectDb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connecto {
	static Connection con=null;
	static Statement stmt=null;
	static PreparedStatement pstmt=null;
	static String uname="root";
	static String password="toor";
	static String url="jdbc:mysql://localhost:3306/hcl";
	List<String> path=new ArrayList<String>();
	public void InsertDb(String fileName,String fileLocation,String userName) {
		try {
//			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				System.out.println("Loading Driver for Insertion");
//			}catch(ClassNotFoundException e) {
//				System.out.println("Class Not Found");
//			}
			con=DriverManager.getConnection(url,uname,password);
			pstmt=con.prepareStatement("INSERT INTO `hcl`.`filenames` (`FileName`, `filePath`,`UserName`) VALUES (?,?,?);");
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileLocation);
			pstmt.setString(3, userName);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("SQL Connection Failed");
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(SQLException e) {
				System.out.println("Exception Occured");
			}
		}
	}
	public List<String> CheckFile(String FileName) {
		try {
//			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				System.out.println("Loading Driver for Checking File");
//			}catch(ClassNotFoundException e) {
//				System.out.println("Class Not Found");
//			}
			con=DriverManager.getConnection(url,uname,password);
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM `hcl`.`filenames` WHERE (`FileName` = '"+FileName+"');");
			if(rs.next()) {
				String data=rs.getString(2);
				path.add(data);
				while(rs.next()) {
					data=rs.getString(2);
					path.add(data);
				}
			}
			else {
				System.err.println("No Log Files Found");
			}
		}catch(SQLException e) {
			System.out.println("SQL Connection Failed");
		}finally {
			try {
				stmt.close();
				con.close();
			}catch(SQLException e) {
				System.out.println("Exception Occured");
			}
		}
		return path;
	}
	public void DeleteDb(String fileLocation) {
		try {
//			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				System.out.println("Loading Driver for Deletion");
//			}catch(ClassNotFoundException e) {
//				System.out.println("Class Not Found");
//			}
			con=DriverManager.getConnection(url,uname,password);
			pstmt=con.prepareStatement("DELETE FROM `hcl`.`filenames` WHERE (`filePath` = ?);");
			pstmt.setString(1, fileLocation);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("SQL Connection Failed");
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(SQLException e) {
				System.out.println("Exception Occured");
			}
		}
	}
}
