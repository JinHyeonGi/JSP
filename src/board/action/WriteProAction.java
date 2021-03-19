package board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;

// 게시글 추가 처리
public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		BoardVO article = new BoardVO (
				Integer.parseInt(request.getParameter("num")),
				request.getParameter("writer"), 
				request.getParameter("email"),
				request.getParameter("subject"),
				request.getParameter("pass"),
				Integer.parseInt(request.getParameter("ref")),
				Integer.parseInt(request.getParameter("step")),
				Integer.parseInt(request.getParameter("depth")),
				new Timestamp(System.currentTimeMillis()),
				request.getParameter("content"),
				request.getRemoteAddr()
			);
		BoardDAO dbPro = BoardDAO.getInstance();
		dbPro.insertArticle(article);
		return "/board/writePro.jsp";
	}

}
