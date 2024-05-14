<%@page import="com.itwillbs.goods.FakeVO"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<FakeVO> fakeList = (List<FakeVO>) request.getAttribute("fake");

	// Gson을 사용하여 List<FakeVO> 객체를 JSON 형식의 문자열로 변환
	Gson gson = new Gson();
	String jsonData = gson.toJson(fakeList);
%>
<%=jsonData%>