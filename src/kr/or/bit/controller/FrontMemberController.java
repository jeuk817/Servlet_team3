package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.JoinFormService;
import kr.or.bit.service.LoginService;
import kr.or.bit.service.LogoutService;
import kr.or.bit.service.MainService;
import kr.or.bit.service.MemberDeleteService;
import kr.or.bit.service.MemberListService;
import kr.or.bit.service.MemberSearchService;
import kr.or.bit.service.SignUpService;

@WebServlet("*.do")
public class FrontMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontMemberController() {
        super();
    }

//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//	}

//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

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
    	
    	if(urlCommand.equals("/Main.do")) { // Get Main page
    		action = new MainService();
    		forward = action.execute(request, response);
    	} else if(urlCommand.equals("/JoinForm.do")) {
    		action = new JoinFormService();
    		forward = action.execute(request, response);
    	} else if(urlCommand.equals("/Login.do")) {
    		action = new LoginService();
    		forward = action.execute(request, response);
    	} else if(urlCommand.equals("/Logout.do")) {
	    	action = new LogoutService();
	    	forward = action.execute(request, response);
    	} else if(urlCommand.equals("/MemberList.do")) {
	    	action = new MemberListService();
	    	forward = action.execute(request, response);
    	} else if(urlCommand.equals("/MemberSearch.do")) {
	    	action = new MemberSearchService();
	    	forward = action.execute(request, response);
    	} else if(urlCommand.equals("/MemberDelete.do")) {
	    	action = new MemberDeleteService();
	    	forward = action.execute(request, response);
    	} else if(urlCommand.equals("/SignUp.do")) {
	    	action = new SignUpService();
	    	forward = action.execute(request, response);
    	}
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false (모든 자원 ) 사용
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}

}
