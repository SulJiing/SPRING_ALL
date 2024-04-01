package kr.or.ddit.property.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PropertyVO;

//@Repository
public class PropertyDAOImpl_template implements PropertyDAO {
	
//	@Inject // 원래는 private이라 외부에 있는 컨테이너가 접근하지 못하지만 스프링의 리플렉션이 public으로 바꿈
	private SqlSession sqlSession;
	
	@Inject
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertProperty(PropertyVO newProp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PropertyVO> selectProperties() {
		return sqlSession.selectList("kr.or.ddit.property.dao.PropertyDAO.selectProperties");
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