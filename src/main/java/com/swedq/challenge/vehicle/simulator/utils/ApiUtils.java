package com.swedq.challenge.vehicle.simulator.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

/**
 * API common Utils class HTTP methods  [GET, POST], request  and response handling
 * @author ziaa
 *
 */
public class ApiUtils {

	private CloseableHttpClient mHttpClient; 

	public ApiUtils() {
		mHttpClient = HttpClients.createDefault();
	}
	
/**
 * Generic Http Post Client
 * 
 * @param api API Path
 * @param bodyParams Post Params
 * @param cls API response to be parsed with cls Class
 * 
 * @return T Parsed Class
 */
	public <T> T httpPost(String api,List<NameValuePair> bodyParams,Class<T> cls) {
		HttpPost httpPost = new HttpPost(Constants.API_PATH + api);
		UrlEncodedFormEntity encodedEntity = null;
		try {
			encodedEntity = new UrlEncodedFormEntity(bodyParams, "UTF-8");
			httpPost.setEntity(encodedEntity);
			httpPost.setHeader("authorization", Constants.TEST_JSON_TOKEN);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return postResponse(httpPost, cls);
		
	}
/**
 * Helper method for Post Response
 * 
 * @param httpPost 
 * @param type
 * @return
 */
	private <T> T postResponse(HttpPost httpPost, Class<T> cls) {
		try {
			HttpResponse response = mHttpClient.execute(httpPost);
			HttpEntity respEntity = response.getEntity();
			 if (respEntity != null) {
			        // EntityUtils to get the response content
			        String content =  EntityUtils.toString(respEntity);
			        return genericCast(content, cls);
			    }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 *  Generic Http Get Client
	 *  
	 * @param api API Path
	 * @param cls API response to be parsed with cls Class
	 * 
	 * @return T Parsed Class
	 */
	public <T> T httpGet(String api, Class<T> type) {
		HttpGet request = new HttpGet(Constants.API_PATH + api);
		request.setHeader("authorization", Constants.TEST_JSON_TOKEN);
		try (CloseableHttpResponse response = mHttpClient.execute(request)) {
			
	            HttpEntity entity = response.getEntity();
	            if (entity != null) {
	                // return it as a String
	            	String responseStr = EntityUtils.toString(entity);
	            	return  genericCast(responseStr, type);
//	                
	            }

	        }catch(Exception  e) {
	        		e.printStackTrace();
	        }

		return null;
	}
	
	/**
	 * GSON based helper utility to map incoming JSON to given class type.
	 * 
	 * @param content API  response
	 * @param cls Target class
	 * 
	 * @return T Parsed Class
	 */
	private <T> T genericCast(String content, Class<T> cls) {
		return new Gson().fromJson(content,cls);
	}
	
}
