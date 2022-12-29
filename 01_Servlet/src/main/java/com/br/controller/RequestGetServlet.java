package com.br.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 */
@WebServlet("/test1.do") // Servlet을 지칭하는 고유한 url mapping값 // java코드 수정시 server restart해주기
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get방식으로 요청시 해당 doGet메소드가 자동으로 호출됨
		// System.out.println("잘 실행되나?");
		
		/*
		 * 첫번재 매개변수인 request에는 요청과 관련된 내용들 담겨있음 (요청전송방식, 요청자의 ip주소, 요청시 전달된 값들 등)
		 * 두번째 매개변수인 response는 요청 처리 후 응답할 때 사용되는 객체
		 * 
		 * 요청 처리를 위해서 전달된 값 뽑기
		 * request의 parameter 영역 안에 존재 (키=밸류 세트로 담겨있음)
		 * 
		 * request의 parameter 영역으로부터 전달된 데이터 뽑는 메소드
		 * > request.getParameter("키") : String (그에 해당하는 value값)
		 * > request.getParametervalues("키") : String[] (그에 해당하는 value값들이 배열에 담겨서 반환)
		 */
		
		String name = request.getParameter("name"); // "홍길동" | ""(빈문자열)
		String gender = request.getParameter("gender"); // "M" | "F" | null // radio버튼이나 checkbox는 아무것도 체크 안할 시 key값조차 넘어오지 않아서 null이 반환 => NullPointerException예외발생
		int age = Integer.parseInt(request.getParameter("age")); // "20" => 20 | "" => NumberFormatException예외발생
		String city = request.getParameter("city"); // "서울" | "경기도" | ... // 빈문자열 넘어올 수 없음
		double height = Double.parseDouble(request.getParameter("height")); // "160" => 160.0 // 빈문자열 넘어올 수 없음
		
		// 체크박스와 같은 복수개의 밸류값들을 뽑고자 할 때
		String[] foods = request.getParameterValues("food"); // ["한식", "일식"] | null
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		
		if(foods == null) {
			System.out.println("foods : 없음");
		} else {
			System.out.println("foods : " + String.join("/", foods)); // join("연결자", 배열) 메소드
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
