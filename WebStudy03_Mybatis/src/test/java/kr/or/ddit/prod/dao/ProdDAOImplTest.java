package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProdDAOImplTest {

	ProdDAO dao = new ProdDAOImpl();
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testInsertProd() {
		ProdVO prod = new ProdVO();
		prod.setProdLgu("P101");
		prod.setProdId("123");
		prod.setProdName("123");
		prod.setProdProperstock(123);
		prod.setProdTotalstock(123);
		prod.setProdImg("123");
		prod.setProdOutline("123");
		prod.setProdSale(123);
		prod.setProdPrice(123);
		prod.setProdCost(123);
		prod.setProdBuyer("P10101");
		dao.insertProd(prod);
	}

	@Test
	void testSelectTotalRecord() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectProdList() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000006");
		assertNotNull(prod);
		assertNotNull(prod.getLprod());
		log.info("prod : {}",prod);
	}

	@Test
	void testUpdateProd() {
		fail("Not yet implemented");
	}

}
