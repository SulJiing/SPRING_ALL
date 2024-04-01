package kr.or.ddit.address.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AddressVO;

public class AddressDAOImpl implements AddressDAO {
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<AddressVO> selectAllAddress() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			return sqlSession.selectList("kr.or.ddit.address.dao.AddressDAO.selectAllAddress");
		}
	}

	@Override
	public AddressVO selectAddress(String adrsName) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			return sqlSession.selectOne("kr.or.ddit.address.dao.AddressDAO.selectAddress",adrsName);
		}
	}

	@Override
	public int insertAddress(AddressVO address) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(); // true로 두면 오토커밋이 됨
		) {
			int rowcnt = sqlSession.insert("kr.or.ddit.address.dao.AddressDAO.insertAddress",address);
			sqlSession.commit();
			return rowcnt;
		}
	}

	@Override
	public int updateAddress(AddressVO address) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAddress(String adrsName) {
		// TODO Auto-generated method stub
		return 0;
	}
}
