package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;

// 글 목록 처리
public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String pageNum = request.getParameter("pageNum"); // 현재 페이지 번호
		if(pageNum == null) {
			pageNum = "1";
		}
		// 페이징 처리
		int pageSize = 5; // 한 페이지의 글 갯수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작 글번호
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호
		int count = 0;
		int number = 0;
		
		List<BoardVO> articleList = null;
		BoardDAO dbPro = BoardDAO.getInstance();
		count = dbPro.getArticleCount();
		// 게시글이 있으면 조회한 글 목록을, 없으면 빈 리스트를 articleList에 담는다.
		if(count > 0) articleList = dbPro.getArticles(startRow, endRow);
		else articleList = Collections.emptyList();
		
		number = count - (currentPage - 1) * pageSize; // 글 목록에 표시할 번호
		
		// 뷰에서 사용할 속성 담기
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("articleList", articleList);
		return "/board/list.jsp";
	}

}
