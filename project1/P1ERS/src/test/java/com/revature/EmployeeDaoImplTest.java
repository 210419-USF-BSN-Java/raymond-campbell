package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.utilities.ConnectionFactory;

public class EmployeeDaoImplTest {

	private static EmployeeDao e;
	Connection conn = ConnectionFactory.getConnectionFromEnv();
	
	@BeforeClass
	public static void setUp() {
		e = new EmployeeDaoImpl();
	}
	
	@Before
	public void beforeEach() {
		
	}
	
	@After
	public void afterEach() {
		
	}
	
	@AfterClass
	public static void tearDown() {
		
	}
	
	@Test
	public void testInstantiation() {
		assertNotNull(e);
	}

	@Test
	public void testSuccessQuery() {
		int testId;
		
		String sql = "select product_id from mock.cars where product_id = 1";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				testId = rs.getInt(1);
				assertNotNull(testId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void testUnsuccessfulQuery() {
		int testId;
		
		String sql = "select * from mock.cars where product_id = 9999";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				testId = rs.getInt(1);
				assertEquals(testId,0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSuccessQueryWithParam() {
		int testId = 1;
		
		String sql = "select product_id from mock.cars where product_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, testId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				testId = rs.getInt(1);
				assertNotNull(testId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUnSuccessQueryWithParam() {
		int testId = 9999;
		
		String sql = "select product_id from mock.cars where product_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, testId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				testId = rs.getInt(1);
				assertEquals(testId,0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSuccessQueryWithMultipleParam() {
		int testId = 1;
		String testName = "Fredrick";
		
		String sql = "select product_id, make from mock.cars where product_id = ? and make = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, testId);
			ps.setString(2, testName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				testId = rs.getInt(1);
				testName = rs.getString(2);
				assertEquals(1, testId);
				assertEquals("Fredrick", testName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUnSuccessQueryWithMultipleParam() {
		int testId = 0;
		String testName = "fake";
		
		String sql = "select product_id, make from mock.cars where product_id = ? and make = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, testId);
			ps.setString(2, testName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				testId = rs.getInt(1);
				testName = rs.getString(2);
				assertEquals(0, testId);
			    assertNull(testName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertStatement() {
		int testId = 22;
		int retrievedId;

		String sql = "insert into mock.cars (product_id) values (?);";
		String query = "select * from mock.cars where product_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			PreparedStatement ps2 = conn.prepareStatement(query);
			ps.setInt(1, testId);
			ps2.setInt(1, testId);
			
			ps.executeUpdate();
			
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				retrievedId = rs2.getInt(1);
				assertEquals(retrievedId,testId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteStatement() {
		int testId = 5;
		int retrievedId;

		String sql = "delete from mock.cars where product_id = ?;";
		String query = "select * from mock.cars where product_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			PreparedStatement ps2 = conn.prepareStatement(query);
			ps.setInt(1, testId);
			ps2.setInt(1, testId);
			
			ps.executeUpdate();
			
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				retrievedId = rs2.getInt(1);
				assertNotEquals(retrievedId,testId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStatement() {
		int testId = 7;
		int retrievedId;

		String sql = "update mock.cars set product_id = ? where product_id = ?;";
		String query = "select * from mock.cars where product_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			PreparedStatement ps2 = conn.prepareStatement(query);
			ps.setInt(1, testId);
			ps.setInt(2, testId);
			ps.executeUpdate();
			
			ps2.setInt(1, testId);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				retrievedId = rs2.getInt(1);
				assertEquals(retrievedId,testId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
}
