package com.pin.sms.rest.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public interface ApiResponseBuilderUtil {

	public static<T> APIResponse<T> toApiResponse(ResponseTypes resType,T data){
		return new APIResponse<>(resType.getCode(), resType.getStatus(), data);
	}
	
	
	public static<T> ResponseEntity<APIResponse<T>> toResponseEntity(ResponseTypes resType,T data,HttpStatus httpStatus){
		return new ResponseEntity<>(toApiResponse(resType, data),httpStatus);
	}
	
	public static<T> ResponseEntity<T> toResponseEntity(T data,HttpStatus httpStatus){
		return new ResponseEntity<>(data,httpStatus);
	}
	
	public static String readOGMetaTag(String httpUrl, String tag) throws IOException {
		URL url=null;
		try {
			url=new URL(httpUrl);
			HttpsURLConnection con=(HttpsURLConnection)url.openConnection();
			con.setReadTimeout((int)TimeUnit.SECONDS.toMillis(10));
			con.setConnectTimeout((int)TimeUnit.SECONDS.toMillis(10));
			InputStream is=con.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			String line=null;
			String videoOgTag=MessageFormat.format("<meta property=\"{0}\" content=\"",tag);
			String endTag="\" />";
			String videoUrl="";
			while(( line=br.readLine())!=null) {
				if(line.contains(videoOgTag)) {
					int start=line.indexOf(videoOgTag)+videoOgTag.length();
					int end=line.indexOf(endTag, start);
					videoUrl=line.substring(start,end);
					break;
				}
			}
			br.close();
			con.disconnect();
			return videoUrl.replace("&amp;", "&");
		} catch (IOException e) {
			throw new IOException("Unable to fetch OG tag "+tag+" from url "+httpUrl, e);
		}
	}
	
}
