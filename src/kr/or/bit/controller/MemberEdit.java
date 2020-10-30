package kr.or.bit.controller;

import java.io.IOException;

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

@WebServlet("/MemberEdit")
public class MemberEdit extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public MemberEdit() {
        super();
    }

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   HttpSession session = request.getSession();
		
	   if(session.getAttribute("userId") == null || !Middlewares.isAdmin(session)) {
		   response.sendRedirect("Login");
	   } else {
		   this.doGet(request, response);
	   }
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");
      String id = request.getParameter("id");
      KoreaMemberDao dao = new KoreaMemberDao();
      KoreaMember km = dao.getKoreaMember(id);
      
      request.setAttribute("KoreaMember", km);
      RequestDispatcher rd = request.getRequestDispatcher("Ex03_MemberEdit.jsp");
      rd.forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.sendRedirect("Main");
   }

}
