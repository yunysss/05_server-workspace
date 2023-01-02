package com.br.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("doGet메소드 실행");
		
		// 1) 요청시 전달 값 뽑기 (request의 parameter으로부터)
		//	  post방식 요청 같은 경우 데이터들을 뽑기 "전"에 인코딩 설정해야됨
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String city = request.getParameter("city");
		double height = Double.parseDouble(request.getParameter("height"));
		
		String[] foods = request.getParameterValues("food");
		
		System.out.println(name);
		System.out.println(gender);
		System.out.println(age);
		System.out.println(city);
		System.out.println(height);
		System.out.println(foods);
		
		// 2) 요청 처리 (db와 상호작용)
		
		// 3) 성공/실패 판단 후 응답페이지
		//	  순수 Servlet 방식 : Java 코드 내에서 html을 기술
		//	  JSP(Java Server Page) 방식 : html 내에서 Java코드를 기술
		
		// 응답페이지를 만드는 과정을 jsp에게 위임 (떠넘기기)
		// 위임시 필요한 객체 == RequestDispatcher
		/*
		RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp");
		view.forward(request, response);
		*/
		// 단, 현재 servlet상에 존재하는 데이터가 응답화면(jsp)에서 필요할 경우
		// 그 데이터를 어딘가에 주섬주섬 담아서 jsp로 전달해야함
		// 응답할 데이터를 주섬주섬 담기위한 공간 == request의 attribute영역 (키-밸류 세트로 담아야함)
		// request.setAttribute("키", 밸류);
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("gender", gender);
		request.setAttribute("foods", foods); // 어떤 타입의 객체든 다 담을 수 있음
		
		// 응답하고자 하는 뷰(jsp) 선택하면서 RequestDispatcher생성 => 포워딩
		RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost메소드 실행");
		doGet(request, response);
	}

}
