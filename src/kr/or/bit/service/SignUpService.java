package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMember;

public class SignUpService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String ip = request.getRemoteAddr();
		
		KoreaMember km = new KoreaMember(id, pwd, name, age, gender, email, ip);
		KoreaMemberDao.insertKoreaMember(km);
		
		forward.setRedirect(true);
		forward.setPath("Main.do");
		
		return forward;
	}

}
