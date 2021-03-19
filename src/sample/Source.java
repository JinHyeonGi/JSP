package sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Source")
public class Source extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Source Start");
		// 페이지 이동
		// 1. forward
		// url이 안바뀐다.
		// 요청 응답 객체가 유지된다.
		// 속도가 빠르다
		// request
//		RequestDispatcher view = request.getRequestDispatcher("Destination");
//		view.forward(request, response);
		
		// 2. redirect
		// url이 바뀐다.
		// 요청 응답 객체가 유지되지 않는다.
		// 속도가 느리다
		// response
		response.sendRedirect("Destination");
	}
}
