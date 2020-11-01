package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.middleware.Middlewares;

public class MemberDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userId") == null || !Middlewares.isAdmin(session)) {
			forward.setRedirect(true);
			forward.setPath("Login.do");
		} else {
			String id = request.getParameter("id");
			KoreaMemberDao koreaMemberDao = new KoreaMemberDao();
	        koreaMemberDao.deleteKoreaMember(id);
	        
	        forward.setRedirect(true);
	        forward.setPath("MemberList.do");
		}
		return forward;
	}
	
}
