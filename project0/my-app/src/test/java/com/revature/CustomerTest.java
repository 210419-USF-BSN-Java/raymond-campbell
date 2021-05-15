package com.revature;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.Customer;
import com.revature.utilities.ConnectionFactory;

public class CustomerTest {
	
	private static Customer c;
	boolean ordering = true;
	Scanner sc = new Scanner(System.in);
	Connection conn = ConnectionFactory.getConnectionFromEnv();
	
	@BeforeClass
	public static void setUp() {
		c = new Customer(0, null, null, null, null);
	}
	
	@Before
	public void beforeEach() {
		System.out.println("About to run test: ");
	}
	
	@After
	public void afterEach() {
		System.out.println("After the test...");
	}
	
	@AfterClass
	public static void tearDown() {
		
	}
	
	@Test
	public void testInstantiation() {
		assertNotNull(c);
	}
	
	@Test
	public void testSuccessQuery() {
		int testId;
		
		String sql = "select emp_id from mock.employees where emp_id = 1";
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
		
		String sql = "select emp_id from mock.employees where emp_id = 9999";
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
		
		String sql = "select emp_id from mock.employees where emp_id = ?";
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
		
		String sql = "select emp_id from mock.employees where emp_id = ?";
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
		
		String sql = "select emp_id, first_name from mock.employees where emp_id = ? and first_name = ?";
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
		
		String sql = "select emp_id, first_name from mock.employees where emp_id = ? and first_name = ?";
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
}
