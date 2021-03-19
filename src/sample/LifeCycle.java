package sample;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet 과 DD(web.xml)을 같이 쓰면 에러가 발생한다.
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LifeCycle() {
        super();
        System.out.println("LifeServlet 생성자 호출");
    }
    
	public void init(ServletConfig config) throws ServletException {
		System.out.println("LifeServlet init() 호출");
	}

	public void destroy() {
		System.out.println("LifeServlet destroy() 호출");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeServlet service() 호출");
	}

}
