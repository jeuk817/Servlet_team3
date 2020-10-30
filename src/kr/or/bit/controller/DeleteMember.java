package kr.or.bit.controller;

import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMember;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteMember")
public class DeleteMember extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession httpSession = request.getSession();
        //삭제할 id의 정보를 dao로 보내서 해당 데이터를 가져옴
        String id = request.getParameter("id");
        KoreaMemberDao koreaMemberDao = new KoreaMemberDao();
        int resultRow = koreaMemberDao.deleteKoreaMember(id);
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        
        RequestDispatcher rd = request.getRequestDispatcher("Ex03_Memberlist.jsp");
        rd.forward(request, response);
    }
}