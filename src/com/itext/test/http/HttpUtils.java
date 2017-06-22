package com.itext.test.http;

import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpUtils {
	
	public static String doPostByForm(String uri,List<NameValuePair> params){
		String result=null;
		try {
			HttpPost httppost = new HttpPost(uri);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);  
			httppost.setEntity(entity); 
//			httppost.addHeader("app_id", "appid");
			
			CloseableHttpClient httpclient = HttpClients.createDefault(); 
			CloseableHttpResponse response=httpclient.execute(httppost);
			HttpEntity resentity = response.getEntity();
			if(null!=resentity){
				result=EntityUtils.toString(resentity);
				response.close(); 
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static String doPostByString(String uri,String params){
		String result=null;
		try {
			HttpPost httppost = new HttpPost(uri);
			StringEntity entity = new StringEntity(params);
			httppost.setEntity(entity); 
			CloseableHttpClient httpclient = HttpClients.createDefault(); 
			CloseableHttpResponse response=httpclient.execute(httppost);
			HttpEntity resentity = response.getEntity();
			if(null!=resentity){
				result=EntityUtils.toString(resentity);
				response.close(); 
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public static String doPostByJson(String uri,String json){
		String result=null;
		try {
			HttpPost httppost = new HttpPost(uri);
			httppost.addHeader("Content-Type", "application/json;charset=utf-8");
			StringEntity entity = new StringEntity(json,"UTF-8");
			httppost.setEntity(entity); 
			CloseableHttpClient httpclient = HttpClients.createDefault(); 
			CloseableHttpResponse response=httpclient.execute(httppost);
			HttpEntity resentity = response.getEntity();
			if(null!=resentity){
				result=EntityUtils.toString(resentity);
				response.close(); 
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static String doGetByParm(String uri){
		String result=null;
		try {
			HttpGet httpget = new HttpGet(uri);
		
			CloseableHttpClient httpclient = HttpClients.createDefault(); 

//			HttpHost proxy = new HttpHost("127.0.0.1", 7777, "http");
//			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();  
//			httpget.setConfig(config);
			
			
			CloseableHttpResponse response=httpclient.execute(httpget);
			HttpEntity resentity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			
			if(null!=resentity){
				result=EntityUtils.toString(resentity);
				response.close(); 
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static String doPutByJson(String uri,String json){
		String result=null;
		try {
			HttpPut httpput = new HttpPut(uri);
			httpput.addHeader("Content-Type", "application/json;charset=utf-8");
			StringEntity entity = new StringEntity(json,"UTF-8");
			httpput.setEntity(entity); 
			CloseableHttpClient httpclient = HttpClients.createDefault(); 
			CloseableHttpResponse response=httpclient.execute(httpput);
			HttpEntity resentity = response.getEntity();
			if(null!=resentity){
				result=EntityUtils.toString(resentity);
				response.close(); 
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
