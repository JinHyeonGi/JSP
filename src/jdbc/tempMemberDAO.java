package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class tempMemberDAO {

//	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
//	private final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
//	private final String USER = "mytest";
//	private final String PASS = "mytest";
//	private ConnectionPool pool = null;
	
	// WAS에서 제공하는 Connection Pool
	private Connection conn = null;
	
	public tempMemberDAO() {
//		try {
//			Class.forName(JDBC_DRIVER);
//			pool = ConnectionPool.getInstance();
//		}catch (Exception e) {
//			System.out.println("Error: JDBC 드라이버 로딩 실패");
//		}
	}
	
	private Connection getConnection() {
		try {
			// Context 설정 파일
			Context init = new InitialContext();
			// Context -> Resource 접근 후 DataSource 가져오기
			// 커넥션 풀을 관리해주는 객체 (DB와의 연동을 관리)
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/myOracle");
			conn = ds.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Vector<tempMemberVO> getMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector<tempMemberVO> vecList = new Vector<tempMemberVO>();
		
		try {
//			conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
//			conn = pool.getConnection();
			conn = this.getConnection();
			String sql = "select * from tempmember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				tempMemberVO vo = new tempMemberVO(rs.getString("id"), rs.getString("passwd"), rs.getString("name"),
						rs.getString("mem_num1"), rs.getString("mem_num2"), rs.getString("e_mail"), rs.getString("phone"),
						rs.getString("zipcode"), rs.getString("address"), rs.getString("job"));
				vecList.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
//					pool.releaseConnection(conn);
					conn.close(); // 컨테이너가 알아서 close하면 처리해준다.
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vecList;
	}
}
