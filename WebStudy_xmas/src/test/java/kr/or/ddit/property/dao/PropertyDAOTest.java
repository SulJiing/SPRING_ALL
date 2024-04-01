package kr.or.ddit.property.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

//import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.PropertyVO;

class PropertyDAOTest {
	
	PropertyDAO dao = new PropertyDAOImpl();

	@Test
	void testInsertProperty() {
		PropertyVO newProp = new PropertyVO();
		newProp.setPropertyName("dummyName");
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
		for(int i=1; i<=200; i++) { // StressTest
			PropertyVO vo = dao.selectProperty("NLS_CALENDAR");
			assertNotNull(vo);
		}
	}

	@Test
	void testUpdateProperty() {
		PropertyVO modifyProp = new PropertyVO();
		modifyProp.setPropertyName("fail.common.msg");
		modifyProp.setPropertyValue("modified value");
		int rawcnt = dao.updateProperty(modifyProp);
		assertEquals(1, rawcnt);
		
		modifyProp.setPropertyName("afsewhdtmthsragrtrhtrthesh");
		modifyProp.setPropertyValue("modified value");
		rawcnt = dao.updateProperty(modifyProp);
		assertEquals(0, rawcnt);
	}

	@Test
	void testDeleteProperty() {
		int rowcnt = dao.deleteProperty("NLS_COMP");
		assertEquals(1, rowcnt);
	}
}
