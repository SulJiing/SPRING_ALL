package kr.or.ddit.lecture.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;

public class OthersDAOImpl implements OthersDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public Map<String, Object> selectSubject() {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
				OthersDAO mapperProxy = sqlSession.getMapper(OthersDAO.class);
				return mapperProxy.selectSubject();
		}
	}

	@Override
	public List<Map<String, Object>> selectSubjectList() {
			try(
					SqlSession sqlSession = sqlSessionFactory.openSession();
					){
					OthersDAO mapperProxy = sqlSession.getMapper(OthersDAO.class);
					return mapperProxy.selectSubjectList();
			}
	}

}
