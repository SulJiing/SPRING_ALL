package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 *	Factory Object(Method) Pattern
 *	: 객체의 생성을 전담하는 객체를 별도로 운영하는 구조.
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;
	
//	kr.or.ddit.db.dbInfo
	static { // static code block - ConnectionFactory가 메모리에 로딩될 때 한번만 실행
		ResourceBundle dbInfo = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo");
		url = dbInfo.getString("url");
		user = dbInfo.getString("user");
		password = dbInfo.getString("password");
		
		BasicDataSource bds = new BasicDataSource();
		dataSource = bds;
		bds.setDriverClassName(dbInfo.getString("driverClassName"));
		bds.setUrl(url);
		bds.setUsername(user);
		bds.setPassword(password);
		// BasicDataSource 정책(Polling정책)
		// 초기 생성 갯수
		bds.setInitialSize(Integer.parseInt(dbInfo.getString("initialSize")));
		// 연결 다 차면 다음 요청이 기다리는 시간
		bds.setMaxWaitMillis(Long.parseLong(dbInfo.getString("maxWait")));
		// 연결이 생기면 더 생성함 - 그런데도 2초 동안 반납이 안되면 SQLException
		bds.setMaxTotal(Integer.parseInt(dbInfo.getString("maxTotal")));
		// 놀고있을 때 갯수
		bds.setMaxIdle(Integer.parseInt(dbInfo.getString("maxIdle")));
		
		// 커넥션 풀이 대신 해줌
//		try {
//			Class.forName(dbInfo.getString("driverClassName")); // 퀄러파이드 네임
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		} 
	}
	// static - 객체를 생성하지 않아도 됨
	public static Connection getConnection() throws SQLException {
//		Connection conn = DriverManager.getConnection(url, user, password);
		Connection conn = dataSource.getConnection();
		return conn;
	}
}