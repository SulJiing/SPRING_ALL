package kr.or.ddit.dept.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.DeptVO;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
@Slf4j
class DeptDAOTest {

	@Inject
	DeptDAO dao;
	
	@Test
	void testSelectDeptList() {
		PaginationInfo paging = new PaginationInfo(4,2);
		paging.setCurrentPage(1);
		List<DeptVO> list = dao.selectDeptList(paging);
		log.info("list : {}",list);
	}
}
