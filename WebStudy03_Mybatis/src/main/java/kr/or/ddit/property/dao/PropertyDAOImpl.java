package kr.or.ddit.property.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PropertyVO;

public class PropertyDAOImpl implements PropertyDAO {
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertProperty(PropertyVO newProp) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession();
			) {
//				int rowcnt = sqlSession.insert("kr.or.ddit.property.dao.PropertyDAO.insertProperty",newProp);
				PropertyDAO mapperproxy = sqlSession.getMapper(PropertyDAO.class);
				int rowcnt = mapperproxy.insertProperty(newProp);
				sqlSession.commit();
				return rowcnt;
			}
	}

	@Override
	public List<PropertyVO> selectProperties() {
		
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession();
			) {
				return sqlSession.selectList("kr.or.ddit.property.dao.PropertyDAO.selectProperties");
			}
	}

	@Override
	public PropertyVO selectProperty(String propertyName) {
		
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession();
			) {
//				return sqlSession.selectOne("kr.or.ddit.property.dao.PropertyDAO.selectProperty",propertyName);
				PropertyDAO mapperProxy = sqlSession.getMapper(PropertyDAO.class);
				return mapperProxy.selectProperty(propertyName);
			}
	}

	@Override
	public int updateProperty(PropertyVO modifyProp) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession();
			) {
				int rowcnt = sqlSession.update("kr.or.ddit.property.dao.PropertyDAO.updateProperty",modifyProp);
				sqlSession.commit();
				return rowcnt;
			}
	}

	@Override
	public int deleteProperty(String propertyName) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession();
			) {
//				int rowcnt = sqlSession.delete("kr.or.ddit.property.dao.PropertyDAO.deleteProperty",propertyName);
				PropertyDAO mapperproxy = sqlSession.getMapper(PropertyDAO.class);
				int rowcnt = mapperproxy.deleteProperty(propertyName);
				sqlSession.commit();
				return rowcnt;
			}
		}
	}