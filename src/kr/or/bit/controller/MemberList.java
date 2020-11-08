package kr.or.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMember;
import kr.or.bit.middleware.Middlewares;

@WebServlet("/MemberList")
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberList() {
        super();
    }
    
//    private static boolean isAdmin(HttpSession session) {
//    	String userId = (String)session.getAttribute("userId");
//    	return userId.equals("admin");
//    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userId") == null || !Middlewares.isAdmin(session)) {
			response.sendRedirect("Login");
		} else {
			this.doGet(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberList doGet");
		KoreaMemberDao dao = new KoreaMemberDao();
		List<KoreaMember> memberList = dao.getKoreaMemberList();
		
		RequestDispatcher rd = request.getRequestDispatcher("Ex03_Memberlist.jsp");
//		RequestDispatcher rd = request.getRequestDispatcher("test1.jsp");
		request.setAttribute("memberList", memberList);
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Main");
	}


}
