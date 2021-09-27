package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	 
	


}
