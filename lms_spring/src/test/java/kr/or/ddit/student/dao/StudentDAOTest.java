package kr.or.ddit.student.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
class StudentDAOTest {
	
	@Inject
	StudentDAO dao;
		
	@Test
	void testSelectStudentList() {
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(1);
		List<StudentVO> list = dao.selectStudentList(paging);
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
	void testSelectStudent() {
		StudentVO stu = dao.selectStudent("23BA100007");
		log.info("\n\n\nstu : {}\n\n\n",stu);
	}

	@Test
	void testSelectStudentSubList() {
		List<StudentVO> list = dao.selectStudentSubList("231AA102");
		log.info("\n\n\nlist : {}\n\n\n",list);
	}

	@Test
	void testInsertStudent() {
		StudentVO vo = new StudentVO();
		vo.setStdNo("24BA010039");
		vo.setStdName("김학교");
		vo.setStdId("050218-4231582");
		vo.setStdTelno("010-5681-3566");
		vo.setStdAddress("충남 아산시");
		vo.setProNo("24A02");
		dao.insertStudent(vo);
	}

	@Test
	void testUpdateStudent() {
		StudentVO vo = new StudentVO();
		vo.setStdNo("24BA010039");
		vo.setStdName("김학생");
		vo.setStdId("050218-4231582");
		vo.setStdTelno("010-5681-3566");
		vo.setStdAddress("충남 아산시");
		vo.setProNo("24A02");
		dao.updateStudent(vo);
	}

}
