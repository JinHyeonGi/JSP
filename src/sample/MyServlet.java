package sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/MyServlet") -> 여기도 기본적으로 컨텍스트 루트까지 잡아주는 듯 하다.
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><title>My First Html</title></head>");
			out.println("<body>");
			out.println("<h2>My First Html File</h2>");
			out.println("<br><hr color='red'><br>");
			out.println("<div align='center'>");
			out.println("지금은");
			out.println(new java.util.Date());
			out.println(" 입니다.");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		}finally {
			out.close();
		}
	}

}
