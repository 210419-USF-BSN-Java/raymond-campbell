package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utilities.ConnectionFactory;
import com.revature.utilities.PasswordHashing;
import com.revature.utilities.ProxyAppTwo;

public class ManagerDaoImpl implements ManagerDao{
	Connection c = ConnectionFactory.getConnectionFromEnv();
	Scanner sc = new Scanner(System.in);
	boolean ordering = true;
	Logger loggy = Logger.getLogger(ProxyAppTwo.class);
	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User authenticateUser(String username, String password) {
		
		User u = new User(0, username, password, null, null, null, 0);
		String sql = "select * from project_one.users u where username = ? and password = ? and role_id = 2;";
		
		PreparedStatement ps;
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, PasswordHashing.doHashing(password));
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				u.setUserId(rs.getInt(1));
				u.setPassword(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setRoleId(rs.getInt(7));
				
				System.out.println(u.getUserId());
				
				if(u.getUserId() > 0) {
					loggy.info(u.getUsername() + " has logged in.");
					return u;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reimbursement> viewAllReimbRequests() {
		List<Reimbursement> requests = new ArrayList<>();
		String sql = "select * from project_one.reimbursements r where reimb_status_id = 1;";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				requests.add(new Reimbursement(
						rs.getInt(1),
						rs.getDouble(2),
						rs.getString(5),
						rs.getInt(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getInt(10)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public void viewAllReimbReceipts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reimbursement> viewResolvedRequests() {
		List<Reimbursement> requests = new ArrayList<>();
		String sql = "select * from project_one.reimbursements r where reimb_status_id != 1;";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				requests.add(new Reimbursement(
						rs.getInt(1),
						rs.getDouble(2),
						rs.getString(5),
						rs.getInt(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getInt(10)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public List<User> viewAllEmployees() {
		List<User> results = new ArrayList<>();
		String sql = "select * from project_one.users u where role_id = 1;";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				results.add(new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}

	@Override
	public Reimbursement viewReimbRequestById(int Id) {
		String sql = "select * from project_one.reimbursements r where reimb_id = ?;";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, Id);
			ResultSet rs = ps.executeQuery();
			Reimbursement result = new Reimbursement(
					rs.getInt(1),
					rs.getDouble(2),
					rs.getString(5),
					rs.getInt(7),
					rs.getInt(8),
					rs.getInt(9),
					rs.getInt(10));
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void submitReimbReceipt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean approveReimbRequest(int managerId, int reimbId) {
		String sql = "update project_one.reimbursements set reimb_resolver = ?, reimb_status_id = 2 where reimb_id = ?;";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, managerId);
			ps.setInt(2, reimbId);
			if(ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean denyReimbRequest(int managerId, int reimbId) {
		String sql = "update project_one.reimbursements set reimb_resolver = ?, reimb_status_id = 3 where reimb_id = ?;";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, managerId);
			ps.setInt(2, reimbId);
			if(ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public User getUserById(int userId) {
		
		User u = new User(0, null, null, null, null, null, 0);
		String sql = "select * from project_one.users u where user_id = ?;";
		
		PreparedStatement ps;
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				u.setUserId(rs.getInt(1));
				u.setPassword(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setRoleId(rs.getInt(7));
				
				if(u.getUserId() > 0) {
//					loggy.info(u.getUsername() + " has logged in.");
					return u;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}