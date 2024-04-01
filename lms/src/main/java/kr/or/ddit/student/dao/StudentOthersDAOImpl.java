package kr.or.ddit.student.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;

public class StudentOthersDAOImpl implements StudentOthersDAO{

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int selectTotalRecord(PaginationInfo paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			StudentOthersDAO mapperProxy = sqlSession.getMapper(StudentOthersDAO.class);
			return mapperProxy.selectTotalRecord(paging);
		}
	}

	@Override
	public List<Map<String, Object>> selectStudentList(PaginationInfo paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			StudentOthersDAO mapperProxy = sqlSession.getMapper(StudentOthersDAO.class);
			return mapperProxy.selectStudentList(paging);
		}
	}

	@Override
	public Map<String, Object> selectStudent (String stdNo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			StudentOthersDAO mapperProxy = sqlSession.getMapper(StudentOthersDAO.class);
			System.out.println(stdNo);
			return mapperProxy.selectStudent(stdNo);
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> selectStudentClazz(String stdNo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			StudentOthersDAO mapperProxy = sqlSession.getMapper(StudentOthersDAO.class);
			System.out.println(stdNo);
			return mapperProxy.selectStudentClazz(stdNo);
		}
	}

}
