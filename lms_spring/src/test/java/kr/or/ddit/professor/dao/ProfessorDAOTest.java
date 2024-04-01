package kr.or.ddit.professor.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.ProfessorVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
class ProfessorDAOTest {
	
	@Inject
	ProfessorDAO dao;
	
	@Test
	void testSelectProfessorList() {
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(1);
		List<ProfessorVO> list = dao.selectProfessorList(paging);
		log.info("\n\n\nlist : {}\n\n\n",list);
	}

	@Test
	void testSelectTotalRecord() {
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(1);
		int cnt = dao.selectTotalRecord(paging);
		log.info("\n\n\ncnt : {}\n\n\n",cnt);
	}

	@Test
	void testSelectProfessor() {
		ProfessorVO pro = dao.selectProfessor("22A06");
		log.info("\n\n\npro : {}\n\n\n",pro);
	}

	@Test
	void testInsertProfessor() {
		ProfessorVO pro = new ProfessorVO();
		pro.setProNo("24A02");
		pro.setProName("테스트");
		pro.setProMajor("정치외교");
		pro.setProTelno("010-1234-1682");
		int cnt = dao.insertProfessor(pro);
		log.info("\n\n\ncnt : {}\n\n\n",cnt);
	}

	@Test
	void testUpdateProfessor() {
		ProfessorVO pro = new ProfessorVO();
		pro.setProNo("24A02");
		pro.setProName("김이름");
		pro.setProMajor("정치외교");
		pro.setProTelno("010-1234-1682");
		int cnt = dao.updateProfessor(pro);
		log.info("\n\n\ncnt : {}\n\n\n",cnt);
	}

}
