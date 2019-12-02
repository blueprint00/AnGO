package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {

	private DBManager() {
	}

	public static DBManager getInstance() throws Exception {

		return DBManagerLazyHolder.instance;
	}

	private static class DBManagerLazyHolder {

		private static final DBManager instance = new DBManager();
	}

	public Connection getConnection() throws NamingException, SQLException, Exception {

		Connection conn = null;

		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/final_ango");
		conn = ds.getConnection();

		if (conn != null) {
			System.out.println("connect success");
		} else {

			System.out.println("connect fail");
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement pstmt, Statement stmt, ResultSet rs) {

		try {

			if (rs != null) {
				rs.close();
				rs = null;
			}

			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}

			if (stmt != null) {
				stmt.close();
				stmt = null;
			}

			if (conn != null) {
				conn.close();
				conn = null;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
