package kr.or.ddit.clazz.dao;

import java.util.List;
import java.util.Map;

import javax.print.PrintException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.student.dao.StudentOthersDAO;
import kr.or.ddit.vo.ClazzVO;

public class ClazzOthersDAOImpl implements ClazzOthersDAO {

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<Map<String, Object>> selectStudentClazz(String subCd) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ClazzOthersDAO mapperProxy = sqlSession.getMapper(ClazzOthersDAO.class);
			return mapperProxy.selectStudentClazz(subCd);
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateClazz(ClazzVO clazz) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ClazzOthersDAO mapperProxy = sqlSession.getMapper(ClazzOthersDAO.class);
			int rowcnt = mapperProxy.updateClazz(clazz);
			sqlSession.commit();
			return rowcnt;
		}
	}

}
