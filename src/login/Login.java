package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			// 세션은 각 브라우저마다 생성과 동시에 고유의 세션아이디를 가지고 있고 그 세션아이디를 통해 브라우저별로 세션 객체를 생성/반환하고 구분한다. 
			// request.getSession(false) => 요청 온 브라우저 세션아이디에 해당하는 세션이 존재하면 그 세션을 반환하고 없으면 null을 반환한다.
			// => 접속자마다 각각의 고유한 세션객체가 생성된다.
			HttpSession session = request.getSession(false);
			System.out.println("Login");
			if(session != null) {
				String sessionId = session.getId();
				System.out.println("세션 아이디 : " + sessionId);
				String user = (String) session.getAttribute("user");
				
				out.println("<html>");
				out.println("<body>");
				out.println("<table align='center' width='300' border='1'>");
				out.println("<tr>");
				out.println("<td width='300' align='center'>" + user + "님이 로그인 되었습니다.</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align='center'>");
				out.println("<a href='#'>회원정보</a>");
				out.println("<a href='Logout'>로그아웃</a>"); // 상대(현재)경로 로그아웃 (get)
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			}else {
				out.println("<html>");
				out.println("<body>");
				out.println("<form action='LoginCheck' method='post'>"); // form-post요청
				out.println("<table align='center' width='300' border='1'>");
				out.println("<tr>");
				out.println("<th width='100'>아이디</th>");
				out.println("<td width='200'><input type='text' name='id'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th width='100'>비번</th>");
				out.println("<td width='200'><input type='password' name='pwd'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td colspan='2' align='center'>");
				out.println("<input type='button' value='회원가입'>");
				out.println("<input type='submit' value='로그인'>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			}
		} finally {
			out.close();
		}
	}

}
