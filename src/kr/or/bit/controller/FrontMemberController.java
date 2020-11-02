package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.*;

@WebServlet("*.do")
public class FrontMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontMemberController() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlCommand = requestURI.substring(contextPath.length());
    	String method = request.getMethod();
    	System.out.println(method + " " + urlCommand);
	
    	Action action=null;
    	ActionForward forward=null;
    	
    	if(urlCommand.equals("/Main.do")) { // 메인 페이지
    		action = new MainService();
    		forward = action.execute(request, response);
    	} else if(urlCommand.equals("/JoinForm.do")) { // 회원가입 페이지
    		action = new JoinFormService();
    		forward = action.execute(request, response);
    	} else if(urlCommand.equals("/SignUp.do")) { // 회원가입
	    	action = new SignUpService();
	    	forward = action.execute(request, response);
    	} else if(urlCommand.equals("/Login.do")) { // 로그인
    		action = new LoginService();
    		forward = action.execute(request, response);
    	} else if(urlCommand.equals("/Logout.do")) { // 로그아웃
	    	action = new LogoutService();
	    	forward = action.execute(request, response);
    	} else if(urlCommand.equals("/MemberList.do")) { // 회원관리 페이지
	    	action = new MemberListService();
	    	forward = action.execute(request, response);
    	} else if(urlCommand.equals("/MemberSearch.do")) { // 회원이름으로 검색
	    	action = new MemberSearchService();
	    	forward = action.execute(request, response);
    	} else if(urlCommand.equals("/MemberDelete.do")) { // 회원 삭제
	    	action = new MemberDeleteService();
	    	forward = action.execute(request, response);
    	} else if(urlCommand.equals("/MemberDetail.do")) { // 회원 상세정보 페이지
	    	action = new MemberDetailService();
	    	forward = action.execute(request, response);
    	} else if(urlCommand.equals("/MemberEditForm.do")) { // 회원 수정 페이지
	    	action = new MemberEditFormService();
	    	forward = action.execute(request, response);
    	} else if(urlCommand.equals("/MemberEdit.do")) { // 회원 수정
	    	action = new MemberEditService();
	    	forward = action.execute(request, response);
    	}
    	
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	} else { // 404
    		RequestDispatcher dis  = request.getRequestDispatcher("/WEB-INF/views/error/Error404.jsp");
    		dis.forward(request, response);
    	}
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}

}
