package mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"*.doo"},
		initParams = {
				@WebInitParam(name="propertyConfig", value="Command.properties")
		})
public class Controller extends HttpServlet {

	// 명령어와 명령어 처리맵
	private Map<String, Object> commandMap = new HashMap<>();
	
	// properties 파일 읽어오기
	@Override
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("propertyConfig");
		
		Properties pr = new Properties();
		String path = config.getServletContext().getRealPath("/WEB-INF");
		FileInputStream f = null;
		
		try {
			f = new FileInputStream(new File(path, props));
			pr.load(f);
		}catch (Exception e) {
			throw new ServletException(e);
		}finally {
			if(f != null) try {f.close();} catch (Exception e2) {}
		}
		
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			
			try {
				// 클래스 동적 로딩
				Class commandClass = Class.forName(className);
				// 리플렉션
				Object commandInstance = commandClass.newInstance();
				commandMap.put(command, commandInstance);
			}catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	// 요청을 분석해서 해당 작업을 처리
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandProcess com = null;
		try {
			String command = request.getRequestURI();
			System.out.println("getRequestURI : " + command);
			System.out.println("getContextPath : " + request.getContextPath());
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			System.out.println("ReGetRequestURI : " + command);
			com = (CommandProcess)commandMap.get(command);
			view = com.requestPro(request, response);
		}catch (Throwable e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
