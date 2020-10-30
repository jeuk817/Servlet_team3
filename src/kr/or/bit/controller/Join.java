package kr.or.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import kr.or.bit.utils.MyUtils;
import net.sf.json.JSONObject;

@WebServlet("/Join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Join() {
        super(); 
    }
	protected void doJoin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("join");
		request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	String method=request.getMethod();
    	if(method.equals("GET")) {
			this.doGet(request, response);
		} else if(method.equals("POST")) {
			
			this.doPost(request, response);
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("join doGet");        
        RequestDispatcher rd = request.getRequestDispatcher("Ex02_JDBC_JoinForm.jsp");
        rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("join doPost");
		String id = request.getParameter("id");
		System.out.println("id: ");
		KoreaMemberDao kmDao = new KoreaMemberDao();
		KoreaMember km = kmDao.getKoreaMember(id);		
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
//		response.setCharacterEncoding("UTF-8");
		if(km == null) {
			out.write("ok");
			
		}else{
			out.write("fail");
		}
			
		
	
	}
}
