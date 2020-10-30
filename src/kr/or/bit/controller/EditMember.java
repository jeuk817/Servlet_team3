package kr.or.bit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMember;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class EditMember
 */
@WebServlet("/EditMember")
public class EditMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //session의 정보값을 받아옴. session이 admin일 경우
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
        HttpSession httpSession = request.getSession();
        KoreaMember dto = new KoreaMember();
        //id를 dao로 보내서 해당 데이터를 가져옴
        String id = request.getParameter("id");
        dto.setId(id);
        //가져온 데이터를
        String name = request.getParameter("name");
        dto.setName(name);

        int age = Integer.parseInt(request.getParameter("age"));
        dto.setAge(age);

        String email = request.getParameter("email");
        dto.setEmail(email);

        String gender = request.getParameter("gender");
        dto.setGender(gender);
        System.out.println("id: " + id +" name: " + name +" age: " + age +" email: " + email + " gender :" + gender);


        KoreaMemberDao koreaMemberDao = new KoreaMemberDao();
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        int resultRow = koreaMemberDao.updateKoreaMember(dto);
        System.out.println("resultRow: " + resultRow);
        if (resultRow > 0) {
            //업데이트성공
//            RequestDispatcher rd = new RequestDispatcher("Ex03_Memberlist.jsp")
//            rd.forward(request,response);
            out.write("success");
        } else {
            //업데이트 실패시
            out.write("Fail");

        }
	}

}



