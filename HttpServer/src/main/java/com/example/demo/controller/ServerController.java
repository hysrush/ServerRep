package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServerController {

	
	@RequestMapping(value="/getForm")
	public String getForm(HttpServletRequest request, HttpServletResponse response) {
		return "getForm";
	}
	
	@RequestMapping(value="/postForm")
	public String postForm(HttpServletRequest request, HttpServletResponse response) {
		return "postForm";
	}
	
	@RequestMapping(value="/getSubmit")
	public void getSubmit(HttpServletRequest request) {
	    try {
	        CloseableHttpClient httpclient = HttpClients.createDefault();
	        //GET 방식으로 parameter를 전달
	        HttpGet httpGet = new HttpGet("http://localhost:8080/getServer?param1="+request.getParameter("param1")+"param2="+request.getParameter("param2"));
	        CloseableHttpResponse response = httpclient.execute(httpGet);
	        try {
	            System.out.println(response.getStatusLine());
	            //API서버로부터 받은 JSON 문자열 데이터
	            System.out.println(EntityUtils.toString(response.getEntity()));
	            HttpEntity entity = response.getEntity();
	            EntityUtils.consume(entity);
	        } finally {
	            response.close();
	        }  
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//Post Submit
	@RequestMapping(value="/postSubmit")
	public void postSubmit(HttpServletRequest request) {
	    try {
	        CloseableHttpClient httpclient = HttpClients.createDefault();
	        HttpPost httpPost = new HttpPost("http://localhost:8080/postServer");
	        //전달하고자 하는 PARAMETER를 List객체에 담는다
	        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	        nvps.add(new BasicNameValuePair("param1", request.getParameter("param1")));
	        nvps.add(new BasicNameValuePair("param2", request.getParameter("param2")));
	        //UTF-8은 한글
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
	        CloseableHttpResponse response = httpclient.execute(httpPost);
	        try {
	            System.out.println(response.getStatusLine());
	            //API서버로부터 받은 JSON 문자열 데이터
	            System.out.println(EntityUtils.toString(response.getEntity()));
	            HttpEntity entity = response.getEntity();
	            EntityUtils.consume(entity);
	        } finally {
	            response.close();
	        }  
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	
	
}
