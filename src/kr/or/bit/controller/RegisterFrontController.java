package kr.or.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dao.KoreaMemberDao;


//@WebServlet("/web.do")  >> command
//실제로 들어오는 주소는 같아요
//web.do?cmd=register 회원가입
//web.do?cmd=registerlist 회원목록

//Url 방식 > 주소가 고정되면 안되요
//"*.do"  > a.do , b.do , aaaaa.do 
//register.do 회원가입
//registerlist.do 회원목록
@WebServlet("*.do")
public class RegisterFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청 받기
    	//String command  = request.getParameter("cmd")
    	//Url 방식은 cmd parameter 없어요
    	
    	//주소값
    	//register.do
    	//registerok.do
    	//regiseterlist.do
    	
    	//주소 요청의 판단 근거 (함수)
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	//requestURI :  /WebServlet_7_Member_Model2_Mvc_Url/Register.do
    	//contextPath : /WebServlet_7_Member_Model2_Mvc_Url
    	//url_Command : /Register.do
    	
    	System.out.println("requestURI : " + requestURI);
    	System.out.println("contextPath : " + contextPath);
    	System.out.println("url_Command : " + url_Command);
    	
    	//추가코드////////////////////////////////////////////
    	//redirect 와 path 정보를 갖는 클래스
    	//Action 인터페이스 타입은 모든 자식객체의 주소값을 가질 수 있다
    	ActionForward forward=null;
    	Action action=null;
    	///////////////////////////////////////////////////
    	//2. 요청 판단 처리 (화면 , 처리)
    	String viewpage="";
    	if(url_Command.equals("DeleteMember.do")) { //계정 삭제
  
    		 HttpSession httpSession = request.getSession();
    		 System.out.println(httpSession+"1st");
    	        //삭제할 id의 정보를 dao로 보내서 해당 데이터를 가져옴
    	        String id = request.getParameter("id");
    	        KoreaMemberDao koreaMemberDao = new KoreaMemberDao();
    	        int resultRow = koreaMemberDao.deleteKoreaMember(id);
    	        
    	        PrintWriter out = response.getWriter();
    	        forward = new ActionForward();
        		forward.setRedirect(false);
    	        response.setContentType("text/plain");
//    	        response.sendRedirect("MemberList");
    	        
    	       
        		forward.setPath("MemberList");
    	}
    	
    	
    	
    	
        	
    	
    	//4. 뷰 지정하기
    	//RequestDispatcher dis = request.getRequestDispatcher(viewpage);
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false (모든 자원 ) 사용
    			//UI
    			//UI + 로직
    			//forward url 주소 변환 없어 View 내용을 받을 수 있다
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    	
    	
    	//5. forward(request 객체의 주소값을 공유)
    	//dis.forward(request, response);
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
