package kr.or.ddit.property.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import kr.or.ddit.property.dao.PropertyDAO;
import kr.or.ddit.property.dao.PropertyDAOImpl;
import kr.or.ddit.property.dao.inMemoryPropertyDAOimpl;
import kr.or.ddit.vo.PropertyVO;

public class PropertyServiceImpl implements PropertyService {
	
	// 의존관계를 형성하기 위해 의존객체를 직접 생성하는 방식(결합력 최상의 구조)
//	private PropertyDAO dao = new inMemoryPropertyDAOimpl("/kr/or/ddit/message/message-common_en.properties");
	private PropertyDAO dao = new PropertyDAOImpl();

	@Override
	public boolean createProperty(PropertyVO newProp) {
		int rowcnt = dao.insertProperty(newProp);
		return rowcnt>0;
	}

	@Override
	public PropertyVO retrieveProperty(String propertyName) {
		return dao.selectProperty(propertyName);
	}

	@Override
	public List<PropertyVO> retrieveProperties() {
		return dao.selectProperties();
	}

	@Override
	public Set<String> retireveKeySet() {
		return retrieveProperties().stream()
				.map(PropertyVO::getPropertyName)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean updateProperty(PropertyVO modifyProp) {
		return dao.updateProperty(modifyProp) > 0;
	}

	@Override
	public boolean deleteProperty(String propertyName) {
		int rowcnt = dao.deleteProperty(propertyName);
		return rowcnt>0;
	}
}