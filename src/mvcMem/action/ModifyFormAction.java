package mvcMem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcMem.control.ActionForward;
import mvcMem.model.StudentDAO;
import mvcMem.model.StudentVO;

public class ModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StudentDAO dao = StudentDAO.getInstance();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginID");
		StudentVO vo = dao.getMember(id);
		request.setAttribute("vo", vo);
		return new ActionForward("/mvcMem/modifyForm.jsp", false);
	}

}
