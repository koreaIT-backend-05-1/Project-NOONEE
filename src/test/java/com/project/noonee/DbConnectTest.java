package com.project.noonee;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class DbConnectTest {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		
		try(Connection con = 
				DriverManager.getConnection(
						"jdbc:mariadb://web-study.cpxqancqhi5t.ap-northeast-2.rds.amazonaws.com:3306/noonee?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true",
						"admin",
						"1q2w3e4r")){
			System.out.println(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
}
	
