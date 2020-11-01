package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMember;
import kr.or.bit.middleware.Middlewares;

public class MemberEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userId") == null || !Middlewares.isAdmin(session)) {
			forward.setRedirect(true);
			forward.setPath("Login.do");
		} else {
			KoreaMember dto = new KoreaMember();
	        dto.setId(request.getParameter("id"));
	        dto.setName(request.getParameter("name"));
	        dto.setAge(Integer.parseInt(request.getParameter("age")));
	        dto.setGender(request.getParameter("gender"));
	        dto.setEmail(request.getParameter("email"));
	        
	        KoreaMemberDao koreaMemberDao = new KoreaMemberDao();
	        koreaMemberDao.updateKoreaMember(dto);
	        
	        forward.setRedirect(true);
	        forward.setPath("MemberList.do");
		}
		
		return forward;
	}

}
