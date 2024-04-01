package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.common.paging.PaginationInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProdDAOTest extends AbstractRootContextTest {

	@Inject 
	ProdDAO mapper;
	
	@Test
	void testSelectProdList() {
		mapper.selectProdList(new PaginationInfo())
			.stream()
			.forEach(p->log.info("{}", p));
	}

	@Test
	void testSelectProd() {
		fail("Not yet implemented");
	}

}
