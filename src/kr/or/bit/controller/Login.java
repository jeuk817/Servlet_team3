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

import com.google.gson.JsonObject;

import kr.or.bit.middleware.Middlewares;
import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMember;
import kr.or.bit.utils.MyUtils;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Login() {
        super();
    }
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login service");
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
		// 로그인 체크
		// request에 로그인된 회원정보 저장
		// doget or dopost 호출
		String method = request.getMethod();
		if(method.equals("GET")) {
			this.doGet(request, response);
		} else if(method.equals("POST")) {
			
			this.doPost(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login doGet");
        
        RequestDispatcher rd = request.getRequestDispatcher("Ex02_JDBC_Login.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login doPost");
		String body = Middlewares.getBody(request);
		JsonObject jsonObj = MyUtils.parseStringToJson(body);
		String id = jsonObj.get("id").getAsString();
		String pwd = jsonObj.get("pwd").getAsString();
		
		KoreaMemberDao kmDao = new KoreaMemberDao();
		KoreaMember km = kmDao.getKoreaMember(id);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
//		response.setCharacterEncoding("UTF-8");
		if(km == null) {
			out.write("not found");
		} else {
			if(pwd.equals(km.getPwd())) {
				HttpSession session = request.getSession();
	            session.setAttribute("userId", km.getId());
	            request.setAttribute("user", km);
//	            session.setAttribute("userPwd", km.getPwd());
	            session.setMaxInactiveInterval(15 * 60);
	            
	            out.write("success");
			} else {
				out.write("incorrect");
			}
		}
	}

}
