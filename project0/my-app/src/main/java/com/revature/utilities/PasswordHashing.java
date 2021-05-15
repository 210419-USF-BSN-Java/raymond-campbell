package com.revature.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
	
	public static String doHashing(String password) {
		try {
			//Code used to instantiate an algorithm object. MD5 is the algorithm we are using.
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			
			//This code hashes the password
			messageDigest.update(password.getBytes());
			
			//This code returns the hashing value for our password
			byte[] resultByteArray = messageDigest.digest();
			
			StringBuilder sb = new StringBuilder();
			
			for (byte b : resultByteArray) {
				sb.append(String.format("%02x", b));
			}
			
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return "";
	}
}
