package com.pin.sms.rest.utils;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import lombok.extern.slf4j.Slf4j;
import net.bull.javamelody.internal.common.LOG;

@Slf4j
public class ValidationUtils {

	private final static LocalTime start = LocalTime.parse( "08:30:00" );
	private final static LocalTime stop = LocalTime.parse( "21:00:00" );
    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

	private static final int COUNTRYCODE = 91;

	private static final Pattern mobilePattern = Pattern.compile("/[^0-9\\-\\_\\(\\)\\s\\s+]/");//. represents single character

	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	public static String filterMsg(String content) {
		return content.trim().replace("/[\\|]/", "|").replace("/\"/", "\"")
				.replace("/[\r]/", "").replace("/(\\\\\\)/i", " ")
				.replace("/[\\`]/", "").replace("/<\\s*script\\b[^>]*\\s*>(.*?)<\\/\\s*script\\s*>/is", "");
	}
	
	public static String validateMessage(String message) {
		return message.replace(Character.getName(13), "")
		.replace("|", "l").replace("\'", "'").replace("\\’", "'")
		.replace("`", "'").replace(Character.getName(92), "\\")
		.replace(";hash", "#").replace("hash;", "#").replace("amp;", "&")
		.replace("plus;", "+").replace("comma;",",").replace("€™","").replace("&amp;","")
		.replace("&amp;","").replace("acirc;","'").replace("acirc;","'").replace("&quot;","\"")
		.replace("€","").replace("/[\\x80-\\xFF]/","");
	}
	
	
	public static long calcMsgUnits(String message, Integer f1, Integer f2) {
	    int length = message.trim().length();
	    return (long) ((length> f1.intValue()) ? Math.ceil((length / f2.intValue())) : Math.ceil((length / f1.intValue())));
	}
	
	public static double getMsgUnit(String messageType, String message) {
	    int unit  = 0;
	    Map<String, Integer> tempMap = new HashMap<>();
		switch (messageType) {
	        case "TXT":
	        	message = filterMsg(message);
	            unit = (message.length() > 160) ? 153 : 160;
	            break;
	        case "USSD":
	            unit = 182;
	            break;
	        case "UNI":
	            unit=(message.length() > 280) ? 268:280;
	            break;
	        case "PICT":
	            unit = 256;
	            break;
	        case "WAP":
	            unit = 266;
	            break;
	        case "BCARD":
	            unit = 266;
	            break;
	        case "FLASH":
	            unit = 256;
	            break;
	        case "UNC":
	            unit=(message.length() > 280)?268:280;
	            break;
	        case "CLICK":
	        	tempMap.put("F1", 160);
	        	tempMap.put("F2", 153);
	        	break;
	        default:
	            unit = 0;
	            break;
	    }
	    return Math.ceil(message.length()/ unit);
	}

	public static boolean checkMsgSubmitTime() {
		LocalTime target = LocalTime.now();
		return ( target.isAfter( start ) && target.isBefore( stop ) ) ;
	}
	
	public static Map<String, Integer> getMsgQryUnit(String messageType) {
		messageType = messageType.toUpperCase();
	    Map<String, Integer> map = new HashMap<>();
	    map.put("F1", 0);
	    map.put("F2", 0);
	    switch (messageType) {
	        case "TXT":
	        	map.put("F1", 160);
	        	map.put("F2", 153);
	            break;
	        case "USSD":
	        	map.put("F1", 182);
	            break;
	        case "UNI":
	        	map.put("F1", 280);
	        	map.put("F2", 268);
	            break;
	        case "PICT":
	        	map.put("F1", 256);
	        	map.put("F2", 256);
	            break;
	        case "WAP":
	        	map.put("F1", 266);
	        	map.put("F2", 266);
	            break;
	        case "BCARD":
	        	map.put("F1", 266);
	        	map.put("F2", 266);
	            break;
	        case "FLASH":
	        	map.put("F1", 256);
	        	map.put("F2", 256);
	        	break;
	        case "CLICK":
	        	map.put("F1", 160);
	        	map.put("F2", 153);
	            break;
	        default:
	            break;
	    }
	    return map;
	}
	
	public static String replaceAll(String replacement) 
	{
	    return Pattern.compile("/[^0-9]/i").matcher(replacement).replaceAll(replacement);
	}

	public static Map<String, Object> validateMobile(String number) {
		/*
		 * if(!mobilePattern.matcher(number).matches()) {
		 * 
		 * return null; }
		 */
		number = replaceAll(number);
		
	    if(number.length() == 10){
	        number = "91" + number;
	    }
	    //international number validation
	    if(!Pattern.compile("/^\\+/").matcher(number).matches())
	        number = "+" +  number;
	    
	    try {
	        PhoneNumber objNumber = phoneUtil.parse(number, "IN");
	        if (!phoneUtil.isValidNumber(objNumber)) {
	        	log.info("Not a valid phone number {} ", number);
	            return null;
	        } else if (phoneUtil.getNumberType(objNumber).compareTo(PhoneNumberType.FIXED_LINE_OR_MOBILE) == 0) {
	        	log.info("Second validation condition {}", number);
	        	return null;
	        } else {
	            var countrycode = objNumber.getCountryCode();
	            var nationalmobile = objNumber.getNationalNumber();
	            var intnumber = countrycode + "" +nationalmobile;
	            var numberMap = new HashMap<String, Object>();
	            numberMap.put("countrycode", countrycode);
	            numberMap.put("intnumber", intnumber);
	            return numberMap;
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return null;
	    }
	}
	
	public static long internationcalc(Long memId,String msg, int f1, int f2,int countrycode, double basePrice, double intsmsrate){
	    int length = msg.trim().length();
	    long unit = (long) ((length > f1) ? Math.ceil(length / f2) : Math.ceil(length/f1));
	    if(countrycode == COUNTRYCODE){
	        intsmsrate=1;
	        basePrice=1;
	    }
	    long finalrate = Math.round(intsmsrate/basePrice);
	    unit = finalrate* unit;
	    return unit;
	}

	
	
}
