package logon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LogonDBBean {

	private static LogonDBBean instance = new LogonDBBean();
	public static LogonDBBean getInstance() {
		return instance;
	}
	private LogonDBBean() {}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/myOracle");
		return ds.getConnection();
	}
	
	public int userCheck(String id, String passwd) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		int x = -1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select passwd from tempmember where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd)) {
					x = 1;
				}else {
					x = 0;
				}
			}else {
				x = -1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch (Exception e2) {}
			if(pstmt != null) try {pstmt.close();}catch (Exception e2) {}
			if(conn != null) try {conn.close();}catch (Exception e2) {}
		}
		return x;
	}
}
