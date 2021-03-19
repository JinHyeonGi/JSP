package mvcMem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDAO {

	// 싱글톤 적용
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
	
	// 커넥션풀에서 커넥션 얻어오기
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/myOracle");
			conn = ds.getConnection();
		}catch (Exception e) {
			
		}
		return conn;
	}
	
	// 아이디 중복 검사
	public boolean idCheck(String id) {
		boolean result = true;
		String sql = "select * from student where id=?";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, id);
			try(ResultSet rs = pstmt.executeQuery();) {
				if(rs.next()) result = false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// zipcode 가져오기
	public Vector<ZipCodeVO> zipcodeRead(String dong) {
		String sql = "select * from zipcode where dong like '" + dong + "%'";
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();
		try (Connection conn = getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql);){
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					ZipCodeVO tempZipcode = new ZipCodeVO(
							rs.getString("zipcode"),
							rs.getString("sido"),
							rs.getString("gugun"),
							rs.getString("dong"),
							rs.getString("ri"),
							rs.getString("bunji")
						);
					vecList.add(tempZipcode);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return vecList;
	}
	
	// 회원 가입
	public boolean memberInsert(StudentVO vo) {
		boolean result = false;
		String sql = "insert into student values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
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
			int rs = pstmt.executeUpdate();
			if(rs > 0) result = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 회원 수정
	public boolean updateMember(StudentVO vo) {
		boolean result = false;
		String sql = "update student set pass=?, phone1=?, phone2=?, phone3=?, "
				+ "email=?, zipcode=?, address1=?, address2=? where id=?";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getPhone1());
			pstmt.setString(3, vo.getPhone2());
			pstmt.setString(4, vo.getPhone3());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipcode());
			pstmt.setString(7, vo.getAddress1());
			pstmt.setString(8, vo.getAddress2());
			pstmt.setString(9, vo.getId());
			int rs = pstmt.executeUpdate();
			if(rs > 0) result = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 로그인 검증
	public int loginCheck(String id, String pass) {
		int result = -1;
		String dbPass = getPass(id);
		if(dbPass.equals("")) return result;
		if(dbPass.equals(pass)) result = 1;
		else result = 0;
		return result;
	}

	// 회원 삭제
	public int deleteMember(String id, String pass) {
		int result = -1;
		String sql = "delete from student where id=?";
		String dbPass = getPass(id);
		if(dbPass.equals(pass)) {
			try (Connection conn = getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);){
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				result = 1;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			result = 0;
		}
		return result;
	}
	
	// 비밀번호 얻어오기
	public String getPass(String id) {
		String dbPass = "";
		String sql = "select pass from student where id=?";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, id);
			try(ResultSet rs = pstmt.executeQuery();) {
				if(rs.next()) dbPass = rs.getString("pass");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dbPass;
	}
	
	// 회원 조회
	public StudentVO getMember(String id) {
		StudentVO vo = null;
		String sql = "select * from student where id=?";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, id);
			try(ResultSet rs = pstmt.executeQuery();) {
				if(rs.next()) {
					vo = new StudentVO(
							rs.getString("id"),
							rs.getString("pass"),
							rs.getString("name"),
							rs.getString("phone1"),
							rs.getString("phone2"),
							rs.getString("phone3"),
							rs.getString("email"),
							rs.getString("zipcode"),
							rs.getString("address1"),
							rs.getString("address2")
						);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
}
