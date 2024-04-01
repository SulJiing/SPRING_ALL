package kr.or.ddit.buyer.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.BuyerVO;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
@Slf4j
class BuyerDAOTest {
	
	@Inject
	@Named("buyerDAO")
	BuyerDAO dao;
	
	PaginationInfo paging = new PaginationInfo();
	
	@Test
	void test() {
		paging.setCurrentPage(1);
		List<BuyerVO> list = dao.selectBuyerList(paging);
		log.info("list : {}",list);
	}
}
