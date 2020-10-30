package kr.or.bit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMember;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet implementation class MemberDetail
 */
@WebServlet("/MemberDetail")
public class MemberDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //사용자로부터 id를 받아옴
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        //받아온 id를 dao를 통해 db로부터 결과값을 가져옴.
        KoreaMemberDao koreaMemberDao = new KoreaMemberDao();

//	    List koreaMemerList = (List) koreaMemberDao.getKoreaMember(id);
        KoreaMember koreaMemberDto = koreaMemberDao.getKoreaMember(id); //객체로 받아옴
        request.setAttribute("KoreaMember", koreaMemberDto);


//	    RequestDispatcher rd = request.getRequestDispatcher("Ex03_MemberDetail.jsp"); //Detailjsp 파일 이름 넣어주기
        RequestDispatcher rd = request.getRequestDispatcher("test.jsp"); //Detailjsp 파일 이름 넣어주기
        rd.forward(request, response);
    }
}
