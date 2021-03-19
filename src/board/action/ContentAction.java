package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;

// 글 내용 처리
public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num")); // 해당 글 번호
		String hit = request.getParameter("hit");
		String pageNum = request.getParameter("pageNum"); // 해당 페이지 번호
		BoardDAO dbPro = BoardDAO.getInstance();
		if(hit.equals("y")) dbPro.upHit(num);
		BoardVO article = dbPro.getArticle(num);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		return "/board/content.jsp";
	}
	
}
