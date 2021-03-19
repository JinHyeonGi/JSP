package boardone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

	// 싱글톤 적용
	private static BoardDAO instance = null;
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	// 게시판 기능 
	
	// 게시글 추가(새글, 답글)
	public void insertArticle(BoardVO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 답변글일 경우를 대비 -> 답변글 1번
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0; // 새글일 경우 ref를 정하기위한 임시 변수
		String sql = "";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(ref) from board");
			rs = pstmt.executeQuery();
			if(rs.next()) number = rs.getInt(1) + 1;
			else number = 1;
			if(num != 0) {
				// 답변글 2번
				sql = "update board set step=step+1 where ref = ? and step > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate(); 
				// 답변글 3번
				step += 1;
				depth += 1;
			}else {
				// 새글 1번
				ref = number;
				// 새글 2번
				step = 0;
				depth = 0;
			}
			sql = "insert into board(num, writer, email, subject, pass, "
					+ "regdate, ref, step, depth, content, ip) "
					+ "values(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPass());
			pstmt.setTimestamp(5, article.getRegdate());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, step);
			pstmt.setInt(8, depth);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
	}
	
	// 전체 글 개수
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			if(rs.next()) x = rs.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
		
		return x;
	}
	
	// 게시글 목록
	/*
	 select rownum rnum, num, writer, subject, ref, step, depth from (
     select num, writer, subject, ref, step, depth from board order by ref desc, step asc);
	 -- 지금 위 상태는 ROWNUM이 가짜 상태이다. 따라서 진짜로 만들어줘야함(완전히 합치는 과정이 필요)
	 select * from (
     select rownum rnum, num, writer, subject, ref, step, depth from (
        	select num, writer, subject, ref, step, depth from board order by ref desc, step asc
  		)	
	 )
	 where rnum >= 1 and rnum <= 5; 
	 */
	public List<BoardVO> getArticles(int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> articleList = new ArrayList<>(end - start + 1);
		try {
			conn = ConnUtil.getConnection();
			// 수정2
			// pstmt = conn.prepareStatement("select * from board order by num desc"); 
			pstmt = conn.prepareStatement("select * from (select rownum rnum, num, writer, email, "
					+ "subject, pass, regdate, readcount, ref, step, depth, content, ip from ("
					+ "select * from board order by ref desc, step asc)) where rnum >= ? and rnum <= ?"); 
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			// 수정3
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO article = new BoardVO(rs.getInt("num"), rs.getString("writer"), 
						rs.getString("email"), rs.getString("subject"), rs.getString("pass"), 
						rs.getInt("readcount"), rs.getInt("ref"), rs.getInt("step"), 
						rs.getInt("depth"), rs.getTimestamp("regdate"), 
						rs.getString("content"), rs.getString("ip"));
				articleList.add(article);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
		return articleList;
	}
	
	// 게시글 가져오기
	public BoardVO getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		try {
			conn = ConnUtil.getConnection();
//			upHit(num);
			pstmt = conn.prepareStatement("select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardVO(rs.getInt("num"), rs.getString("writer"), 
						rs.getString("email"), rs.getString("subject"), rs.getString("pass"), 
						rs.getInt("readcount"), rs.getInt("ref"), rs.getInt("step"), 
						rs.getInt("depth"), rs.getTimestamp("regdate"), 
						rs.getString("content"), rs.getString("ip"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
		return article;
	}
	
	// 수정글 가져오기
	public BoardVO updateGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardVO(rs.getInt("num"), rs.getString("writer"), 
						rs.getString("email"), rs.getString("subject"), rs.getString("pass"), 
						rs.getInt("readcount"), rs.getInt("ref"), rs.getInt("step"), 
						rs.getInt("depth"), rs.getTimestamp("regdate"), 
						rs.getString("content"), rs.getString("ip"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}	
		return article;
	}

	// 게시글 수정
	public int updateArticle(BoardVO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update board set writer=?, email=?, subject=?, content=? where num=?";
		int result = -1;
		try {
			String dbpasswd = getPass(article.getNum());
			if(dbpasswd.equals(article.getPass())) {
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, article.getWriter());
				pstmt.setString(2, article.getEmail());
				pstmt.setString(3, article.getSubject());
				pstmt.setString(4, article.getContent());
				pstmt.setInt(5, article.getNum());
				pstmt.executeUpdate();
				result = 1;
			}else {
				result = 0;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}	
		return result;
	}
	
	// 게시글 삭제
	public int deleteArticle(int num, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete from board where num = ?";
		int result = -1;
		try {
			String dbpasswd = getPass(num);
			System.out.println(dbpasswd);
			if(dbpasswd.equals(pass)) {
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				result = 1;
			}else {
				result = 0;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}	
		return result;
	}
	
	// 게시글 비밀번호 가져오기
	public String getPass(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPass = "";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select pass from board where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) dbPass = rs.getString("pass");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}	
		return dbPass;
	}

	// 조회수 증가
	public void upHit(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("update board set readcount=readcount+1 where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
	}
}
