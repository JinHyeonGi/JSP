package mvc.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.control.ActionForward;

// 작업단위 통합 인터페이스
public interface Action {

	public ActionForward execute(HttpServletRequest request, 
				HttpServletResponse response) throws IOException;
}
