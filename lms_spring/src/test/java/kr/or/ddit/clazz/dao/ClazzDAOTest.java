package kr.or.ddit.clazz.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.vo.ClazzVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
class ClazzDAOTest {
	
	@Inject
	ClazzDAO dao;
	
	@Test
	void testInsertClazz() {
		ClazzVO vo = new ClazzVO();
		vo.setStdNo("23AB100003");
		vo.setSubCd("182AA104");
		vo.setClsScore(0);
		
		int result = dao.insertClazz(vo);
		log.info("\n\n\n결과 : {}\n\n\n",result);
		
	}

	@Test
	void testUpdateClazz() {
		ClazzVO vo = new ClazzVO();
		vo.setStdNo("23AB100003");
		vo.setSubCd("182AA104");
		vo.setClsScore(20);
		
		int result = dao.updateClazz(vo);
		log.info("\n\n\n결과 : {}\n\n\n",result);
	}
	
	@Test
	void testSelectClazz() {
		ClazzVO vo = new ClazzVO();
		vo.setStdNo("23AB100003");
		vo.setSubCd("182AA104");
		
		if(dao.selectClazz(vo)!= null) {
			log.info("\n\n\n결과 : 이미 신청함\n\n\n");			
		}else {
			log.info("\n\n\n결과 : 신청됨\n\n\n");			
		}
	}


}
