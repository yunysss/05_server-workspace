package com.br.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		// 뽑아낸 값(요청시 전달된 값)들을 가지고 요청처리해야됨 (db와 상호작용)
		// > Service 메소드 호출 > Dao 메소드 호출 > DB에 sqㅣ문 실행
		/*
		int result = new MemberService().insertMember(name, gender, age, city, height, foods);
		if(result > 0) {
			// 성공 => 성공페이지
		} else {
			// 실패 => 실패페이지
		}
		*/
		
		// 요청처리 후에 성공했다는 가정하에 사용자가 보게 될 응답페이지(html) 만들어서 전송해보기
		// 즉, 여기 "Java코드 내에서" 사용자가 보게 될 응답 "html 구문을 작성"
		
		// 장점 : Java코드 내에 작성하기 때문에 자바에서의 반복문이나 조건문, 유용한 메소드 활용해서 구성
		// 단점 : 굉장히 복잡, 불편, 혹시라도 html을 수정한다면 결국 Java코드를 수정하는것이기 때무ㅜㄴ에 
		//		 수정된 내용을 반영시키고자 한다면 서버 재실행해야될 수도 있음
		
		// * response객체를 이용해서 사용자에게 html전송
		// 1) 이제부터 내가 출력할 내용은 문서형태의 html이고 문자셋은 utf-8이다 라는 걸 선언
		response.setContentType("text/html; charset=UTF-8");
		// 2) 응답하고자 하는 사용자(요청했던 사용자)와의 스트림 생성
		PrintWriter out = response.getWriter();
		// 3) 저 스트림을 통해 응답 html구문을 한 줄 씩 출력
		out.println("<html>");
		out.println("<head>");
		
		out.println("<style>");
		out.println("h2{color:red}");
		out.println("#name{color:orange}");
		out.println("#age{color:yellow}");
		out.println("#city{color:blue}");
		out.println("#height{color:green}");
		out.println("#gender{color:purple}");
		out.println("</style>");
		
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인정보 응답화면</h2>");
		
		out.printf("<span id='name'>%s</span>님은 ", name);
		out.printf("<span id='age'>%d</span>살이며, ", age);
		out.printf("<span id='city'>%s</span>에 사는 ", city);
		out.printf("키는 <span id='height'>%.1f</span>cm이고 ", height);
		
		out.print("성별은 ");
		if(gender == null) {
			out.println("선택을 안했습니다. <br>");
		} else {
			if(gender.equals("M")) {
				out.println("<span id='gender'>남자</span>입니다. <br>");
			} else {
				out.println("<span id='gender'>여자</span>입니다. <br>");
			}
		}
		
		out.print("좋아하는 음식은 ");
		if(foods == null) {
			out.println("없습니다.");
		} else { // ["한식", "중식", "분식", ..]
			out.println("<ul>");
			
			for(int i=0; i<foods.length; i++) {
				out.println("<li>" + foods[i] + "</li>");
			}
			
			out.println("</ul>");
		}
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
