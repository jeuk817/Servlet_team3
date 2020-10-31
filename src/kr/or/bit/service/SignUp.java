package kr.or.bit.service;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String ip = request.getParameter("ip");

        KoreaMemberDao dao = new KoreaMemberDao();
        KoreaMember dto = new KoreaMember(id, pwd, name, age, gender, email, ip);

        int result = dao.insertKoreaMember(dto);

        String msg = "";
        String url = "";
        if (result > 0) {
            msg = "회원가입 성공!";
            url = "MemberList.do";
        } else {
            msg = "회원가입 실패! 다시 시도해주세요";
            url = "Main.do";
        }
        request.setAttribute("board_msg", msg);
        request.setAttribute("board_url", url);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/redirect.jsp");

        return forward;

    }
}
