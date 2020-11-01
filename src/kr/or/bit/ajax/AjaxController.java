package kr.or.bit.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.or.bit.action.ActionAjax;
import kr.or.bit.action.ActionDataAjax;
import kr.or.bit.ajaxService.LoginService_Ajax;
import kr.or.bit.middleware.Middlewares;
import kr.or.bit.utils.MyUtils;

@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlCommand = requestURI.substring(contextPath.length());
    	request.setAttribute("urlCommand", urlCommand);
    	String method = request.getMethod();
    	System.out.println(method + " " + urlCommand);
    	
		if(method.equals("GET")) {
			this.doGet(request, response);
		} else if(method.equals("POST")) {
			String body = Middlewares.getBody(request);
			JsonObject jsonObj = MyUtils.parseStringToJson(body);
			request.setAttribute("body", jsonObj);
			this.doPost(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionAjax action = null;
		ActionDataAjax dataAjax = null;
		
		if(request.getAttribute("urlCommand").equals("/Login.ajax")) {
			action = new LoginService_Ajax();
			dataAjax = action.execute(request, response);
		}
		
		PrintWriter out = response.getWriter();
		if(dataAjax != null) {
			response.setContentType(dataAjax.getContentType());
			out.print(dataAjax.getData());
		}
		
		out.flush();
	}

}
