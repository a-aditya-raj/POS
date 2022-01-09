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

import org.apache.log4j.Logger;

public class BrandMasterApi {
	
	private static final Logger logger = Logger.getLogger(BrandMasterApi.class);
	private Connection con;
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
	}
	
	public BrandMasterApi() throws Exception {
		Properties props = new Properties();
		InputStream inStream = new FileInputStream("Project.properties");
		props.load(inStream);
		Class.forName(props.getProperty("jdbc.driver"));
		con = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"),
				props.getProperty("jdbc.password"));
	}
	
	public void insert() throws SQLException {
		logger.info("Inserting Brand");
		Statement stmt = con.createStatement();
		for (int i = 0; i < 3; i++) {
			int id = i;
			String brand = "Brand" + i;
			String category = "category" + i;
			String query = "insert into BrandMaster values(" + id + ", '" + brand + "', '" + category + "')";
			stmt.executeUpdate(query);
			logger.info(query);
		}
		stmt.close();
	}

	public ResultSet select() throws SQLException {
		logger.info("selecting Brand");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from BrandMaster");
		return rs;
	}

	public void delete() throws SQLException {
		logger.info("deleting Brand");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from BrandMaster");
		List<Integer> idList = new ArrayList<Integer>();
		while (rs.next()) {
			idList.add(rs.getInt(1));
		}
		for (int i = 0; i < idList.size(); i++) {
			stmt.executeUpdate("delete from BrandMaster where id=" + idList.get(i));
		}
		stmt.close();
	}
}
