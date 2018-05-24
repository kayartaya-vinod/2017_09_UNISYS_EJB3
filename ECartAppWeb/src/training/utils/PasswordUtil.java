package training.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordUtil {
	private PasswordUtil() {
	}

	public static String hashPassword(String password, String algorithm) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(password.getBytes());
			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b1 : b) {
				sb.append(Integer.toHexString(b1 & 0xff).toString());
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
