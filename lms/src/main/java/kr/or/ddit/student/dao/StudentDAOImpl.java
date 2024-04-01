package kr.or.ddit.student.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.StudentVO;

public class StudentDAOImpl implements StudentDAO{

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertStudent(StudentVO student) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			StudentDAO mapperProxy = sqlSession.getMapper(StudentDAO.class);
			int rowcnt = mapperProxy.insertStudent(student);
			sqlSession.commit();
			return rowcnt;
		}catch (Exception e) {
			return 0;
		}
	}

	@Override
	public StudentVO selectStudent(String stdNo) {
		return null;
	}

	@Override
	public int selectTotalRecord(PaginationInfo paging) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudentVO> selectStudentList(PaginationInfo paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateStudent(StudentVO student) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			StudentDAO mapperProxy = sqlSession.getMapper(StudentDAO.class);
			int rowcnt = mapperProxy.updateStudent(student);
			sqlSession.commit();
			return rowcnt;
		}
	}

}
