package kr.or.ddit.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public MemberVO selectMemberForAuth(String memId) {
		try (
			SqlSession sqlSession = sqlSessionFactory.openSession();
		) {
			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMemberForAuth",memId);
		}
	}
//	template method pattern으로 해결
	@Override
	public int insertMember(MemberVO member) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(); // true로 두면 오토커밋이 됨
		) {
			int rowcnt = sqlSession.insert("kr.or.ddit.member.dao.MemberDAO.insertMember",member);
			sqlSession.commit();
			return rowcnt;
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember",memId);
		}
		
	}

	@Override
	public List<MemberVO> selectMemberList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ");
		sql.append(" MEMBER ");
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
//		try (
//			Connection conn = ConnectionFactory.getConnection();
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql.toString());
//		) {
//			
//		}
		return null;
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		try (
			SqlSession sqlSession = sqlSessionFactory.openSession();
		) {
			int rowcnt = sqlSession.insert("kr.or.ddit.member.dao.MemberDAO.deleteMember",memId);
			sqlSession.commit();
			return rowcnt;
		}
	}
}