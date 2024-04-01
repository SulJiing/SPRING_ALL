package kr.or.ddit.property.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.PropertyVO;

class PropertyDAOTest {
	
	PropertyDAO dao = new PropertyDAOImpl();

	@Test
	void testInsertProperty() {
		PropertyVO newProp = new PropertyVO();
		newProp.setPropertyName("dummyNamee");
		newProp.setPropertyValue("dummyValue");
		newProp.setDescription("dummyDesc");
		int rowcnt = dao.insertProperty(newProp);
		assertEquals(1, rowcnt);
	}

	@Test
	void testSelectProperties() {
		List<PropertyVO> propList = dao.selectProperties();
//		assertion
		assert propList != null;
		assertNotNull(propList);
		assertNotEquals(0, propList.size());
	}

	@Test
	void testSelectProperty() {
		for(int i=1; i<=2000; i++) { // StressTest
			PropertyVO vo = dao.selectProperty("NLS_CALENDAR");
			assertNotNull(vo);
		}
	}

	@Test
	void testUpdateProperty() {
		PropertyVO modifyProp = new PropertyVO();
		modifyProp.setPropertyName("NLS_SORT");
		modifyProp.setPropertyValue("modified value");
		modifyProp.setDescription("asd");
		int rawcnt = dao.updateProperty(modifyProp);
		assertEquals(1, rawcnt);
		
//		modifyProp.setPropertyName("afsewhdtmthsragrtrhtrthesh");
//		modifyProp.setPropertyValue("modified value");
//		rawcnt = dao.updateProperty(modifyProp);
//		assertEquals(0, rawcnt);
	}

	@Test
	void testDeleteProperty() {
		int rowcnt = dao.deleteProperty("NLS_COMP");
		assertEquals(1, rowcnt);
	}
}
