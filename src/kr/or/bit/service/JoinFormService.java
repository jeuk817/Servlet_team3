package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class JoinFormService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {
			System.out.println("userId: " + session.getAttribute("userId"));
			forward.setRedirect(true);
			forward.setPath("Login.do");
		} else {
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/JoinForm.jsp");
		}
		
		return forward;
	}
	
}
