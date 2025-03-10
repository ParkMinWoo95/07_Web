package com.kh.burgerking.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.burgerking.model.dto.UserDTO;

@WebServlet("/sign-up.do")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 컨트롤러
		 * 
		 * 1. request객체로부터 값을 뽑아서 DTO로 가공
		 * 2. 요정 처리 --> 패스
		 * 3. VIEW 처리
		 */
		// POST 방식일 경우 인코딩 설정 ISO-8859-1 방식으로 바뀌기 때문에
		// 값을 뽑기 전에 미리 UTF-8 방식으로 변경해주어야함
		request.setCharacterEncoding("UTF-8");
		
		// 1) 값 뽑기
		// request.getParameter("input요소의 name속성값");
		String userId = request.getParameter("userId");
		System.out.println(userId);
		String userPw = request.getParameter("userPw");
		System.out.println(userPw);
		String userName = request.getParameter("userName");
		System.out.println(userName);
		
		UserDTO user = new UserDTO(userId, userPw, userName);
		
		// Service -> DAO -> DB
		// : int
		
		// VIEW처리
		
		// JSP를 이용해서 응답데이터 만들기
		
		// JSP : Java 기반의 서버 사이드 스크립트 언어
		// ASP, PHP
		
		//---------------------------------------------------------
		// 응답화면(JSP)에서 필요한 데이터를 넘겨줄 것 -> request에 담아서
		
		// Attribute => 키 : 밸류 세트로 묶어서 값을 담을 수 있음
		request.setAttribute("user", user);
		request.setAttribute("message", "요청 처리에 성공했습니다");
		
		
		// --------------------------------------------------------
		// 응답페이지를 JSP에게 위임(배정)
		
		// RequestDispatcher
		RequestDispatcher view = request.getRequestDispatcher("/views/response_page.jsp");
		
		// view // request, response
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
