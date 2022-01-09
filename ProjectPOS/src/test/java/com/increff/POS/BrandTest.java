package com.increff.POS;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;

import org.junit.Test;

public class BrandTest {
	@Test
	public void sayHello() throws Exception {
		BrandMasterApi api = new BrandMasterApi();
		api.delete();
		api.insert();
		ResultSet rs= api.select();
		int i=0;
		while(rs.next()) {
			i++;
		}
		assertEquals(3, i);
	}
}
