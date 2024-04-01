package kr.or.ddit.dummy.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.vo.CartVO;
import lombok.extern.slf4j.Slf4j;

// 서버를 안돌리기때문에 classpath를 사용하지 않음
@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
@Slf4j
class DummyDAOTest {

	@Inject
	DummyDAO mapper;
	
	@Test
	void testSelectCartList() {
		List<CartVO> list = mapper.selectCartList();
		log.info("list : {}",list);
	}
}
