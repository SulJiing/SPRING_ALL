package kr.or.ddit.property.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.or.ddit.vo.PropertyVO;

public class PropertyDAOImpl_daoSupport extends SqlSessionDaoSupport implements PropertyDAO {

	@Override
	public int insertProperty(PropertyVO newProp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Inject // DAO의 구현체를 없애도 되는 구조
	PropertyDAO mapperProxy;
	
	@Override
	public List<PropertyVO> selectProperties() {
		// 1. 처음에 사용했던 구조
//		return getSqlSessionTemplate().selectList("kr.or.ddit.property.dao.PropertyDAO.selectProperties");
		// 2. mapperProxy 사용구조 - application에 dao의 구현체를 등록
		PropertyDAO mapperProxy = getSqlSessionTemplate().getMapper(PropertyDAO.class);
		return mapperProxy.selectProperties();
	}

	@Override
	public PropertyVO selectProperty(String propertyname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateProperty(PropertyVO modifyProp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProperty(String propertyName) {
		// TODO Auto-generated method stub
		return 0;
	}
}