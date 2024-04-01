package kr.or.ddit.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.ddit.property.dao.PropertyDAO;
import kr.or.ddit.vo.PropertyVO;
import lombok.extern.slf4j.Slf4j;

// spring을 연동함
@ExtendWith(SpringExtension.class)
// testContainer 생성
@ContextConfiguration("file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
@Slf4j
class PropertiesTBConnectTest {
	static PropertyVO newProp = new PropertyVO();
	@BeforeAll
	static void aa() {
//		PropertyVO newProp = new PropertyVO();
		newProp.setPropertyName("dummy");
		newProp.setPropertyValue("dummy");
		newProp.setDescription("dummy");
	}
	
//	// properties 파일 생성함
//	@Inject
//	@Named("dbInfo")
//	Properties dbInfo;
//	
//	// properties 파일을 dataSource에서 읽음
//	@Inject
//	DataSource dataSource;
//	
//	// 여러개를 만듦
//	@Autowired
//	SqlSessionFactory sqlSessionFactory;
	
	// 실제로 사용하는 한가지
	@Inject
	SqlSession sqlSessionTemplate;
	
	@Test
	void selectPropertyTest() throws SQLException {
		PropertyDAO proxy = sqlSessionTemplate.getMapper(PropertyDAO.class);
		proxy.selectProperty("dummyName");
	}
	@Test
	void selectPropertiesTest() {
		List<PropertyVO> list = sqlSessionTemplate.selectList("kr.or.ddit.property.dao.PropertyDAO.selectProperties");
		log.info("조회결과 : {}",list);
		PropertyDAO proxy = sqlSessionTemplate.getMapper(PropertyDAO.class);
		proxy.selectProperties();
	}
	@Test
	void insertPropertyTest() {
//		PropertyVO newProp = new PropertyVO();
//		newProp.setPropertyName("125863");
//		newProp.setPropertyValue("586");
//		newProp.setDescription("125673");
		PropertyDAO proxy = sqlSessionTemplate.getMapper(PropertyDAO.class);
		int cnt = proxy.insertProperty(newProp);
		log.info("cnt : {}",cnt);
	}
	@Test
	void updatePropertyTest() {
		PropertyVO newProp = new PropertyVO();
		newProp.setPropertyName("dummyName");
		newProp.setPropertyValue("586");
		newProp.setDescription("125673");
		PropertyDAO proxy = sqlSessionTemplate.getMapper(PropertyDAO.class);
		int cnt = proxy.updateProperty(newProp);
		log.info("cnt : {}",cnt);
	}
	@Test
	void deletePropertyTest() {
		PropertyDAO proxy = sqlSessionTemplate.getMapper(PropertyDAO.class);
		int cnt = proxy.deleteProperty("dummyName");
		log.info("cnt : {}",cnt);
	}
}