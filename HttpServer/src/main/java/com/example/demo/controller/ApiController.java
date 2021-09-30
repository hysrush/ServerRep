package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {

	//GET 방식
	@RequestMapping(value="/getServer", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getServer(HttpServletRequest request, HttpServletResponse response) {
	    System.out.println("GET Server Response");
	    Map<String, Object> map = new HashMap<String, Object>();
	    //클라이언트 페이지로 부터 Httpclient로 받은 parameter값
	    map.put("param1", request.getParameter("param1"));
	    map.put("param2", request.getParameter("param2"));
	    map.put("flag", "get");
	    map.put("success", true);
	    return map;
	}
	 
	//POST 방식
	@RequestMapping(value="/postServer", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> postServer(HttpServletRequest request, HttpServletResponse response) {
	    System.out.println("POST Server Response");
	    Map<String, Object> map = new HashMap<String, Object>();
	    //클라이언트 페이지로 부터 Httpclient로 받은 parameter값
	    map.put("param1", request.getParameter("param1"));
	    map.put("param2", request.getParameter("param2"));
	    map.put("flag", "post");
	    map.put("success", true);
	    return map;
	}
	 
	

	@RequestMapping(value = "/getSubmit")
	public String getSubmit(HttpServletRequest request) {
		
		Enumeration eHeader = request.getHeaderNames();
		while (eHeader.hasMoreElements()) {
		    String hName = (String)eHeader.nextElement();
		    String hValue = request.getHeader(hName);
		    System.out.println(hName + " : " + hValue + "");
		}
		
		Enumeration eParams = request.getParameterNames();
		while (eParams.hasMoreElements()) {
		    String pName = (String)eParams.nextElement();
		    String pValue = request.getParameter(pName);
		    System.out.println(pName + " : " + pValue + "");
		}
		
//		System.out.println(request.getHeader("referer"));
//		System.out.println(request.getHeader("connection"));
//		System.out.println(request.getHeader("user-agent"));
//		System.out.println(request.getHeader("host"));
//		System.out.println(request.getHeader("cookie"));
//		System.out.println(request.getHeader("accept"));
//		
//		System.out.println(request.getParameter("param1"));
//		System.out.println(request.getParameter("param2"));
	
		
		return "완료되었습니다.";
		
		/*
		 * try { CloseableHttpClient httpclient = HttpClients.createDefault(); //GET
		 * 방식으로 parameter를 전달 HttpGet httpGet = new
		 * HttpGet("http://localhost:8080/getServer?param1="+request.getParameter(
		 * "param1")+"param2="+request.getParameter("param2")); CloseableHttpResponse
		 * response = httpclient.execute(httpGet); try {
		 * System.out.println(response.getStatusLine()); //API서버로부터 받은 JSON 문자열 데이터
		 * System.out.println(EntityUtils.toString(response.getEntity())); HttpEntity
		 * entity = response.getEntity(); EntityUtils.consume(entity);
		 * 
		 * 
		 * } finally { response.close(); } } catch (Exception e){ e.printStackTrace(); }
		 */
		 

	}

	// Post Submit
	@RequestMapping(value = "/postSubmit" , method=RequestMethod.POST)
	public void postSubmit(@RequestParam(value = "param1") String param1,@RequestParam(value="param2") String param2 ) {
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("http://localhost:8080/postServer");
			// 전달하고자 하는 PARAMETER를 List객체에 담는다
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("param1", param1));
			nvps.add(new BasicNameValuePair("param2", param2));
			// UTF-8은 한글
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				System.out.println(response.getStatusLine());
				// API서버로부터 받은 JSON 문자열 데이터
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
