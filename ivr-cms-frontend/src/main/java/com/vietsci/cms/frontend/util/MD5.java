/**
 * @author ngoc.nguyennhu
 * encode MD5
 */
package com.vietsci.cms.frontend.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class MD5 {
	private static String MD5_ENCODE = "MD5";
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5_ENCODE");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}