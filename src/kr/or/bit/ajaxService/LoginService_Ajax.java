package kr.or.bit.ajaxService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import kr.or.bit.action.ActionAjax;
import kr.or.bit.action.ActionDataAjax;
import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMember;


public class LoginService_Ajax implements ActionAjax {

	@Override
	public ActionDataAjax execute(HttpServletRequest request, HttpServletResponse response) {
		JsonObject body = (JsonObject)request.getAttribute("body");
		String id = body.get("id").getAsString();
		String pwd = body.get("pwd").getAsString();
		KoreaMember km = KoreaMemberDao.getKoreaMember(id);
		
		ActionDataAjax dataAjax = new ActionDataAjax();
		dataAjax.setContentType("text/plain");
		if(km == null || !pwd.equals(km.getPwd())) {
			dataAjax.setData("fail");
		} else {
			HttpSession session = request.getSession();
            session.setAttribute("userId", km.getId());
            session.setMaxInactiveInterval(15 * 60);
            
            dataAjax.setData("success");
		}
		
		return dataAjax;
	}
	
}
