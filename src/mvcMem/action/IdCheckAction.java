package mvcMem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberone.StudentDAO;
import mvcMem.control.ActionForward;

public class IdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StudentDAO dao = StudentDAO.getInstance();
		String id = request.getParameter("id");
		boolean check = dao.idCheck(id);
		request.setAttribute("id", id);
		request.setAttribute("check", check);
		return new ActionForward("/mvcMem/idCheck.jsp", false);
	}
}
