package com.increff.POS;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HelloWorld {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Properties props = new Properties();
		InputStream inStream = new FileInputStream("Project.properties");
		props.load(inStream);
		Class.forName(props.getProperty("jdbc.driver"));
		Connection con = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
		Statement stmt = con.createStatement();
		insert(con);
		select(con);
		//delete(con);
		//select(con);
		con.close();
	}

	private static void insert(Connection con) throws SQLException {
		System.out.println("Inserting Brand");
		Statement stmt = con.createStatement();
		for (int i = 0; i < 3; i++) {
			int id=i;
			String brand = "Brand" + i;
			String category = "category" + i;
			String query = "insert into BrandMaster values(" + id + ", '" + brand + "', '" + category+"')";
			stmt.executeUpdate(query);
		}
		stmt.close();
	}
	private static void select(Connection con) throws SQLException {
		System.out.println("selecting Brand");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from BrandMaster");
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
		}
		stmt.close();
	}
	private static void delete(Connection con) throws SQLException {
		System.out.println("deleting Brand");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from BrandMaster");
		List<Integer> idList = new ArrayList<Integer>();
		while (rs.next()) {
			idList.add(rs.getInt(1));
		}
		for(int i=0; i<idList.size(); i++) {
			stmt.executeUpdate("delete from BrandMaster where id=" + idList.get(i));
		}
		stmt.close();
	}
}
		
		


