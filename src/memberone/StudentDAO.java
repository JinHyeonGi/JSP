package memberone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDAO {
	// 싱글톤 패턴 적용 
	// 접속자 수가 많아질 수록 객체를 생성하게 되면 메모리 부담이 크다.
	private static StudentDAO instance = null;
	
	private StudentDAO() {}
	
	public static StudentDAO getInstance() {
		if(instance == null) {
			synchronized (StudentDAO.class) {
				instance = new StudentDAO();
			}
		}
		return instance;
	}
	
	// 커넥션풀에서 커넥션 가져오기
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/myOracle");
			conn = ds.getConnection();
		}catch (Exception e) {
			System.err.println("Connection 생성실패");
			e.printStackTrace();
		}
		return conn;
	}
	
	// 기능 하나하나를 메서드로 구현
	
	// 아이디 중복 체크
	public boolean idCheck(String id) {
		boolean result = true; // 사용가능
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from student where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				result = false; // 사용불가능
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(rs != null) rs.close();} catch (Exception e2) {}
			try {if(pstmt != null) pstmt.close();} catch (Exception e2) {}
			try {if(conn != null) conn.close();} catch (Exception e2) {}
		}
		return result;
	}
	
	// 우편정보 가져오기
	public Vector<ZipCodeVO> zipcodeRead(String dong) {
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from zipcode where dong like '" + dong + "%'";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipCodeVO tempZipcode = new ZipCodeVO(rs.getString("zipcode"), rs.getString("sido"), 
						rs.getString("gugun"), rs.getString("dong"), rs.getString("ri"), rs.getString("bunji"));
				vecList.addElement(tempZipcode);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(rs != null) rs.close();} catch (Exception e2) {}
			try {if(pstmt != null) pstmt.close();} catch (Exception e2) {}
			try {if(conn != null) conn.close();} catch (Exception e2) {}
		}
		return vecList;
	}
	
	// 회원 가입
	public boolean memberList(StudentVO vo) {
		boolean flag = false; 
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println(vo);
		try {
			String sql = "insert into student values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getZipcode());
			pstmt.setString(9, vo.getAddress1());
			pstmt.setString(10, vo.getAddress2());
			
			int count = pstmt.executeUpdate();
			if(count > 0) {
				flag = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(pstmt != null) pstmt.close();} catch (Exception e2) {}
			try {if(conn != null) conn.close();} catch (Exception e2) {}
		}
		return flag;
	}
	
	// 로그인 검증
	public int loginCheck(String id, String pass) {
		int check = -1;
		String dbPass = getPass(id);
		if(pass.equals(dbPass)) {
			check = 1;
		}else {
			check = 0;
		}
		
		if(dbPass.equals(-1)) check = -1;
		return check;
	}
	
	// 회원정보 가져오기
	public StudentVO getMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO vo = null;
		try {
			String sql = "select * from student where id=?";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new StudentVO(rs.getString("id"), rs.getString("pass"), 
						rs.getString("name"), rs.getString("phone1"), 
						rs.getString("phone2"), rs.getString("phone3"), 
						rs.getString("email"), rs.getString("zipcode"), 
						rs.getString("address1"), rs.getString("address2"));
			}
			System.out.println(vo);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(rs != null) rs.close();} catch (Exception e2) {}
			try {if(pstmt != null) pstmt.close();} catch (Exception e2) {}
			try {if(conn != null) conn.close();} catch (Exception e2) {}
		}
		return vo;
	}
	
	// 회원정보 수정
	public void updateMember(StudentVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println(vo);
		try {
			String sql = "update student set pass=?, phone1=?, phone2=?, phone3=?, email=?,"
						+ " zipcode=?, address1=?, address2=? where id=?";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getPhone1());
			pstmt.setString(3, vo.getPhone2());
			pstmt.setString(4, vo.getPhone3());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipcode());
			pstmt.setString(7, vo.getAddress1());
			pstmt.setString(8, vo.getAddress2());
			pstmt.setString(9, vo.getId());
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(pstmt != null) pstmt.close();} catch (Exception e2) {}
			try {if(conn != null) conn.close();} catch (Exception e2) {}
		}
	}
	
	// 회원탈퇴
	public int deleteMember(String id, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPass = getPass(id);
		String sql = "delete from student where id = ?";
		int result = -1;
		try {
			if(pass.equals(dbPass)) {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				result = 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(rs != null) rs.close();} catch (Exception e2) {}
			try {if(pstmt != null) pstmt.close();} catch (Exception e2) {}
			try {if(conn != null) conn.close();} catch (Exception e2) {}
		}
		return result;
	}
	
	// 비밀번호를 얻어오기
	public String getPass(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pass from student where id=?";
		String dbPass = "-1";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbPass = rs.getString("pass");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(rs != null) rs.close();} catch (Exception e2) {}
			try {if(pstmt != null) pstmt.close();} catch (Exception e2) {}
			try {if(conn != null) conn.close();} catch (Exception e2) {}
		}
		return dbPass;
	}
}
