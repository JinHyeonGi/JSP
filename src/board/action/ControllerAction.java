package board.action;

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

import mvc.CommandProcess;

// URL패턴 및 초기 파라미터 지정
@WebServlet(
		urlPatterns = {"*.do"},
		initParams = {
				@WebInitParam(name="propertyConfig", value="Command.properties")
		})
public class ControllerAction extends HttpServlet {
	
	// 명령어와 명령어 처리 클래스를 저장할 Map
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		// 초기 파라미터 읽어오기
		String props = config.getInitParameter("propertyConfig");
		// 명령어와 처리클래스의 정보를 매핑할 Properties 객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		String path = config.getServletContext().getRealPath("/WEB-INF");
		try {
			// Command.properties 내용을 읽어오기
			f = new FileInputStream(new File(path, props));
			// Properties 객체에 저장
			pr.load(f);
		}catch (Exception e) {
			throw new ServletException(e);
		}finally {
			if(f != null) try {f.close();} catch (Exception e2) {}
		}
		
		// key를 추줄한 반복자 객체 생성
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		while(keyIter.hasNext()) {
			// 키를 하나씩 꺼내서 getProperty로 해당키의 value를 꺼내온다
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try {
				// 클래스 동적 로딩
				Class commandClass = Class.forName(className);
				// 리플렉션
				Object commandInstance = commandClass.newInstance();
				// 명령어와 처리클래스가 매핑된 properties파일을 읽어서 Map에 저장
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

	// 사용자의 요청을 분석해서 해당 작업을 처리
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandAction com = null;
		try {
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			com = (CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
		}catch (Throwable e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
