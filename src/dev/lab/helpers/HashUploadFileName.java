package dev.lab.helpers;

import java.security.MessageDigest;

public class HashUploadFileName {

	public static String getImageName(String name) {
		long systime = System.currentTimeMillis();
		byte[] time = new Long(systime).toString().getBytes();
		byte[] id = name.getBytes();
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(id);
			md5.update(time);
			return toHex(md5.digest());
		}
		catch (java.security.NoSuchAlgorithmException e) {
			System.err.println("Unable to calculate MD5 Digest");
			return e.getMessage();
		}
	}
	
	private static String toHex(byte[] digest) {
		StringBuffer buf = new StringBuffer();
		
		for (int i = 0; i < digest.length; i++)
			buf.append(Integer.toHexString((int) digest[i] & 0x00ff));
		
		return buf.toString();
	}
	
}
