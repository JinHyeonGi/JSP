package sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*@WebServlet(
		description = "초기화 파라미터", 
		urlPatterns = { "/InitParam" }, 
		initParams = { 
				@WebInitParam(name = "tel", value = "010-9531-0114", description = "전화번호"), 
				@WebInitParam(name = "email", value = "stdio@gmail.com", description = "이메일")
		})*/
public class InitParam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String company;
	private String manager;
	private String tel;
	private String email;
	
	public InitParam() {
		super();
	}

	@Override
	public void init() throws ServletException {
		System.out.println("초기화 메소드 수행됨");
		// ServletContext 초기 파라미터 값 읽기 (애플리케이션 범위)
		company = getServletContext().getInitParameter("company"); 
		manager = getServletContext().getInitParameter("manager"); 
		
		// ServletConfig 초기 파라미터 값 읽기 (서블릿 범위)
		tel = getServletConfig().getInitParameter("tel");
		email = getServletConfig().getInitParameter("email");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println("requestURI : " + request.getRequestURI());
		System.out.println("requestURL : " + request.getRequestURL());
		System.out.println("ContextPath : " + request.getContextPath());
		System.out.println("PathInfo : " + request.getPathInfo());
		System.out.println("ServletPath : " + request.getServletPath());
		
		// -- 갑자기 생각나서 쓰는 메모 --
		// forward는 자동으로 ContextPath가 붙는다. 
		// redirect는 절대경로와 상대경로 둘다 가능. (spring이나 c:redirect는 자동으로 ContextPath를 잡아주는듯하다)
		// 일단은 a, action 경로에만 c:url 쓴다고 생각하자.
		// 요청 데이터는 무조건 String으로 넘어온다.
		
		/*
			 기본적으로 http통신은 연결을 유지하지 않기 때문에 정보 유지가 안된다.
			 따라서 연결 유지를 위해 쿠키와 세션을 사용한다.
			(연결을 유지하기 위한 정보를 담은 티켓)
			 
			# 쿠키 
			 쿠키는 클라이언트가 관리한다.
			 쿠키는 서버가 관리를 안해서 서버에 부담이 줄어들지만, 보안적으로 약하다. 따라서 유효시간 설정이 필요하다.
			 쿠키는 배열로 얻어올 수 있다. (나중에 따로 쿠키하나만 가져올 순 있음)
			 단순한 검색, 자동 로그인유무, 팝업창, 관심품목 등 보안적인 요소가 필요없는 경우에 사용한다.
			 
			# 세션
			세션은 서버가 관리를 한다.
			세션아이디가 부여되며 세션아이디를 통해 사용자를 구분한다. (세션아이디는 세션쿠키에 저장된다)
			서버에 부하를 줄 수 있다. 쿠키보다 보안적이 강하다.
			로그인, 회원가입 등 보안적인 요소가 필요한 경우 사용한다.
			
			# 인코딩
			request.setCharacterEncoding("utf-8") => form으로 데이터 전송 시 한글로 전송하면 받는쪽에서 한글이 깨질 수 있기 때문에 꼭 사용한다.
			
			
			# 페이지 이동
			forward vs redirect
		*/
		try {
			out.println("<html>");
			out.println("<body>");
			out.println("<li>회사명: " + company + "</li>");
			out.println("<li>담당자: " + manager + "</li>");
			out.println("<li>전화번호: " + tel + "</li>");
			out.println("<li>이메일: " + email + "</li>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}
}
