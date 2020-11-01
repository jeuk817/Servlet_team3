package kr.or.bit.ajaxService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.ActionAjax;
import kr.or.bit.action.ActionDataAjax;
import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMember;

public class IdentificationService_Ajax implements ActionAjax {

	@Override
	public ActionDataAjax execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		KoreaMember km = KoreaMemberDao.getKoreaMember(id);
		
		ActionDataAjax dataAjax = new ActionDataAjax();
		dataAjax.setContentType("text/plain");
		if(km == null) {
			dataAjax.setData("ok");
		} else {
			dataAjax.setData("fail");
		}
		return dataAjax;
	}
	
}
