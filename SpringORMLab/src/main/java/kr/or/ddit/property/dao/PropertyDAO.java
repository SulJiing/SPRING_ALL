package kr.or.ddit.property.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.PropertyVO;

/**
 *	DAO(Data Access Object) : 데이터의 출처를 대상으로 CRUD를 수행하는 객체.
 *	.properties파일과 Database_properties 뷰라는 2개의 데이터 출처에 대해 
 *	공통적인 접근방법을 정의하는 추상화.
 */
@Mapper
public interface PropertyDAO {
	public int insertProperty(PropertyVO newProp);
	public List<PropertyVO> selectProperties();
	public PropertyVO selectProperty(String propertyname);
	public int updateProperty(PropertyVO modifyProp);
	public int deleteProperty(String propertyName);
}

