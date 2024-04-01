package kr.or.ddit.lecture.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.LectureVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
class LectureDAOTest {
	
	@Inject
	LectureDAO dao;
	
	@Test
	void testSelectLectureListPro() {
		List<LectureVO> list = dao.selectLectureListPro("22A06");
		log.info("\n\n\nlist : {}\n\n\n",list);
	}

	@Test
	void testSelectLectureList() {
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(1);
		
		List<LectureVO> list = dao.selectLectureList(paging);
		log.info("\n\n\nlist : {}\n\n\n",list);
	}

	@Test
	void testSelectTotalRecord() {
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(1);
		
		int cnt = dao.selectTotalRecord(paging);
		log.info("\n\n\ncnt : {}\n\n\n",cnt);
	}

}
