package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public final class ConnectionPool {
	// 커넥션 풀
	// 미리 커넥션을 만들어 놓고 제공
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<Connection> free; // 사용하지 않은 커넥션을 저장하는 변수
	private ArrayList<Connection> used; // 사용중인 커넥션을 저장하는 변수
	private String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	private String user = "mytest";
	private String password = "mytest";
	private int initialCons = 10; // 최초 커넥션 수
	private int maxCons = 20; // 최대 커넥션 수
	private int numCons = 0; // 총 커넥션 수
	private static ConnectionPool cp;
	
	public static ConnectionPool getInstance() {
		try {
			if(cp == null) {
				synchronized (ConnectionPool.class) {
					cp = new ConnectionPool();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cp;
	}
	
	// 싱글톤 패턴 적용
	private ConnectionPool() throws SQLException {
		// 갯수를 지정해서 생성하면 특정 갯수안에서는 속도가 좀 더 빠르다.
		free = new ArrayList<Connection>(initialCons);
		used = new ArrayList<Connection>(initialCons);
		while (numCons < initialCons) {
			addConnection();
		}
	}

	private void addConnection() throws SQLException {
		free.add(getNewConnection());
	}

	private Connection getNewConnection() throws SQLException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(con);
		++numCons;
		return con;
	}
	
	// free -> used (미리 만들어 놓은 커넥션을 제공)
	public synchronized Connection getConnection() throws SQLException {
		if(free.isEmpty()) {
			while(numCons < maxCons) {
				addConnection();
			}
		}
		Connection _con;
		_con = free.get(free.size() - 1);
		free.remove(_con);
		used.add(_con);
		return _con;
	}
	
	// used -> free (커넥션 사용 후 반납)
	public synchronized void releaseConnection(Connection _con) throws SQLException {
		boolean flag = false;
		if(used.contains(_con)) {
			used.remove(_con);
			numCons--;
			flag = true;
		}else {
			throw new SQLException("ConnectionPool에 있지 않네요!!");
		}
		
		try {
			if(flag) {
				free.add(_con);
				numCons++;
			}else {
				_con.close();
			}
		}catch (Exception e) {
			try {
				_con.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// 자원 반납
	public void closeAll() {
		// del used
		for(int i=0; i<used.size(); i++) {
			Connection _con = (Connection)used.get(i);
			used.remove(i--);
			try {
				_con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// del free
		for(int i=0; i<free.size(); i++) {
			Connection _con = (Connection)free.get(i);
			free.remove(i--);
			try {
				_con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getMaxCons() {
		return maxCons;
	}
	
	public int getNumCons() {
		return numCons;
	}
}
