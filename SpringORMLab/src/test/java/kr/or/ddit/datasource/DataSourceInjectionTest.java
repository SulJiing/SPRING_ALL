package kr.or.ddit.datasource;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.vo.PropertyVO;
import lombok.extern.slf4j.Slf4j;

//@SpringJUnitConfig(locations = "file:src/main/resources/kr/or/ddit/datasource-context.xml")
@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
@Slf4j
class DataSourceInjectionTest {
	
	// properties 파일 생성함
	@Inject
	@Named("dbInfo")
	Properties dbInfo;
	
	// properties 파일을 dataSource에서 읽음
	@Inject
	DataSource dataSource;
	
	// 여러개를 만듦
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	// 실제로 사용하는 한가지
	@Autowired
	SqlSession sqlSession;
	
	@Test
	void test() throws SQLException {
		log.info("주입된 properties : {}",dbInfo);
		log.info("주입된 dataSource : {}",dataSource);
		log.info("수립된 connection : {}",dataSource.getConnection());
		log.info("주입된 session factory : {}",sqlSessionFactory);
	}
	
	@Test
	void testOpenSession() {
		log.info("주입된 session : {}",sqlSession);
	}
	
	@Test
	void testJdbcTemplate() {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT PROPERTY_NAME FROM TB_PROPERTIES ");
		List<String> result = template.queryForList(sql.toString(),String.class);
		log.info("result : {}",result);
	}
}
