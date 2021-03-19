package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import boardone.ConnUtil;


// 데이터베이스 쿼리 작업을 수행할 클래스
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
	
	// ------------------------------------------------------ 게시판 기능 
	// 전체 게시글수 가져오기
	public int getArticleCount() {
		int x = 0;
		String sql = "select count(*) from board";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) x = rs.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	// 전체 게시글 가져오기 (페이징처리 적용)
	public List<BoardVO> getArticles(int start, int end) {
		List<BoardVO> articleList = new ArrayList<BoardVO>();
		String sql = "select * from (select rownum rnum, num, writer, email, "
				+ "subject, pass, regdate, readcount, ref, step, depth, content, ip from ("
				+ "select * from board order by ref desc, step asc)) where rnum >= ? and rnum <= ?"; 
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					BoardVO article = new BoardVO(
							rs.getInt("num"), rs.getString("writer"), 
							rs.getString("email"), rs.getString("subject"), 
							rs.getString("pass"), rs.getInt("readcount"), 
							rs.getInt("ref"), rs.getInt("step"), 
							rs.getInt("depth"), rs.getTimestamp("regdate"), 
							rs.getString("content"), rs.getString("ip"));
					articleList.add(article);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}
	
	// 답변글 번호 가져오기
	public int maxNum() {
		int maxNum = 1;
		String sql = "select max(ref) from board";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();) {
			if(rs.next()) maxNum = rs.getInt(1) + 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return maxNum;
	}
	
	// 답변글 수정 메소드
	public void updateAnswer(int ref, int step) {
		String sql = "update board set step=step+1 where ref=? and step>?";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, ref);
			pstmt.setInt(2, step);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 추가
	public void insertArticle(BoardVO article) {
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0;
		String sql = "insert into board(num, writer, email, subject, pass, "
				+ "regdate, ref, step, depth, content, ip) "
				+ "values(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			number = maxNum();
			if(num != 0) {
				updateAnswer(ref, step);
				step = step + 1;
				depth = depth + 1;
			}else {
				ref = number;
				step = 0;
				depth = 0;
			}
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
		}
	}
	
	// 특정 게시글 가져오기
	public BoardVO getArticle(int num) {
		BoardVO article = null;
		String sql = "select * from board where num = ?";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					article = new BoardVO(rs.getInt("num"), rs.getString("writer"), 
							rs.getString("email"), rs.getString("subject"), 
							rs.getString("pass"), rs.getInt("readcount"), 
							rs.getInt("ref"), rs.getInt("step"), 
							rs.getInt("depth"), rs.getTimestamp("regdate"), 
							rs.getString("content"), rs.getString("ip"));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	// 조회수 증가
	public void upHit(int num) {
		String sql = "update board set readcount=readcount+1 where num = ?";
		try (Connection conn = ConnUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			pstmt.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 수정
	public int updateArticle(BoardVO article) {
		String sql = "update board set writer=?, email=?, subject=?, content=? where num=?";
		int result = -1;
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			String dbpasswd = getPass(article.getNum());
			if(dbpasswd.equals(article.getPass())) {
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
		}
		return result;
	}
	
	// 게시글 비밀번호 가져오기
	public String getPass(int num) {
		String sql = "select pass from board where num=?";
		String dbPass = "";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			try(ResultSet rs = pstmt.executeQuery();) {
				if(rs.next()) dbPass = rs.getString("pass");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dbPass;
	}
	
	// 게시글 삭제
	public int deleteArticle(int num, String pass) {
		String sql = "delete from board where num = ?";
		int result = -1;
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			String dbpasswd = getPass(num);
			System.out.println(dbpasswd);
			if(dbpasswd.equals(pass)) {
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				result = 1;
			}else {
				result = 0;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
