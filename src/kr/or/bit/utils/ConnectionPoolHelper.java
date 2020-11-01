package kr.or.bit.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolHelper {
	private static ConnectionPoolHelper instance;
	private static DataSource dataSource = null;
	
	synchronized public static ConnectionPoolHelper getInstance() {
		try {
			if(instance == null) instance = new ConnectionPoolHelper();
		} catch(NamingException e) {
			e.printStackTrace();
		}
		
		return instance;
	}
	
	private ConnectionPoolHelper() throws NamingException {
		System.out.println("ConnectionPoolHelper constructor");
		initFirstConnection();
	}
	
	private void initFirstConnection() throws NamingException {
		setupFirstDriver();
	}
	
	public void setupFirstDriver() throws NamingException {
		Context context = new InitialContext();
		dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		System.out.println("First Connection Created!");
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	/*
	Connection Pool에 free 및 객체 소멸 함수들
	*/
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			freeConnection(con);
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}

	public void freeConnection(Connection con, Statement stmt, ResultSet rs) {
	    try {
	        if (rs != null) rs.close();
	        if (stmt != null) stmt.close();
	        freeConnection(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void freeConnection(Connection con, PreparedStatement pstmt) {
	    try {
	        if (pstmt != null) pstmt.close();
	        freeConnection(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void freeConnection(Connection con, Statement stmt) {
	    try {
	        if (stmt != null) stmt.close();
	        freeConnection(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void freeConnection(Connection con) {
	    try {
	        if (con != null) con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void freeConnection(Statement stmt) {
	    try {
	        if (stmt != null) stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void freeConnection(PreparedStatement pstmt) {
	    try {
	        if (pstmt != null) pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void freeConnection(ResultSet rs) {
	    try {
	        if (rs != null) rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}
