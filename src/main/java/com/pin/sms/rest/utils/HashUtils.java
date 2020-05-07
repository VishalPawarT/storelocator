package com.pin.sms.rest.utils;

import org.springframework.stereotype.Component;

@Component
public class HashUtils {

	public String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}

	  /**
	   * convert into Hexadecimal notation of Unicode.<br>
	   * example)a?\u0061
	   * @param str
	   * @return
	   */
	  public static String toHexString(String str) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < str.length(); i++) {
	      sb.append(toHexString(str.charAt(i)));
	    }
	    return sb.toString();
	  }

	  /**
	   * convert into Hexadecimal notation of Unicode.<br>
	   * example)a?\u0061
	   * @param ch
	   * @return
	   */
	  public static String toHexString(char ch) {
	    String hex = Integer.toHexString((int) ch);
	    while (hex.length() < 4) {
			hex =  "0"  +  hex;
	    }
		hex = "\\u"  +  hex;
	    return hex;
	  }
	  
	  public static String uniToNcr(String unicode) {
		  StringBuilder builder = new StringBuilder();
		  for(var i = 0; i < unicode.length(); i++) {
			  builder.append(String.format("%04X", (int)unicode.charAt(i)));
		   }
		  return builder.toString();
	  }
	
}
