package kr.or.ddit.mbti.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.common.exception.CustomPersistenceException;
import kr.or.ddit.vo.MbtiVO;
import kr.or.ddit.vo.PropertyVO;

public class inMemoryMbtiDAOImpl implements MbtiDAO {
	
	private Map<String, MbtiVO> mbtiMap;

	public inMemoryMbtiDAOImpl() {
		super();
		mbtiMap = new HashMap<>();
		mbtiMap.put("istj",new MbtiVO("istj","1. ISTJ 소금형","/WEB-INF/views/mbti/isfp.html"));
		mbtiMap.put("isfj",new MbtiVO("isfj","2. ISFJ 권력형","/WEB-INF/views/mbti/isfj.html"));
		mbtiMap.put("infj",new MbtiVO("infj","3. INFJ 예언자형","/WEB-INF/views/mbti/infj.html"));
		mbtiMap.put("intj",new MbtiVO("intj","4. INTJ 과학자형","/WEB-INF/views/mbti/intj.html"));
		mbtiMap.put("istp",new MbtiVO("istp","5. ISTP 백과사전형","/WEB-INF/views/mbti/istp.html"));
		mbtiMap.put("isfp",new MbtiVO("isfp","6. ISFP 성인군자형","/WEB-INF/views/mbti/isfp.html"));
		mbtiMap.put("infp",new MbtiVO("infp","7. INFP 잔다르크형","/WEB-INF/views/mbti/infp.html"));
		mbtiMap.put("intp",new MbtiVO("intp","8. INTP 아이디어형","/WEB-INF/views/mbti/intp.html"));
		mbtiMap.put("estp",new MbtiVO("estp","9. ESTP 활동가형","/WEB-INF/views/mbti/estp.html"));
		mbtiMap.put("esfp",new MbtiVO("esfp","10. ESFP 사교형","/WEB-INF/views/mbti/esfp.html"));
		mbtiMap.put("enfp",new MbtiVO("enfp","11. ENFP 스파크형","/WEB-INF/views/mbti/enfp.html"));
		mbtiMap.put("entp",new MbtiVO("entp","12. ENTP 발명가형","/WEB-INF/views/mbti/entp.html"));
		mbtiMap.put("estj",new MbtiVO("estj","13. ESTJ 사업가형","/WEB-INF/views/mbti/estj.html"));
		mbtiMap.put("esfj",new MbtiVO("esfj","14. ESFJ 친선도모형","/WEB-INF/views/mbti/esfj.html"));
		mbtiMap.put("enfj",new MbtiVO("enfj","15. ENFJ 언변능숙형","/WEB-INF/views/mbti/enfj.html"));
		mbtiMap.put("entj",new MbtiVO("entj","16. ENTJ 지도자형","/WEB-INF/views/mbti/entj.html"));
	}

	@Override
	public List<MbtiVO> selectMbtiList() {
//		DB연결
//		StringBuffer sql = new StringBuffer();
//		sql.append(" SELECT TYPE, TITLE, LOGICALPATH ");
//		sql.append(" FROM MBTITABLE ");
//
//		List<MbtiVO> mbtiList = new ArrayList<>();
//		try (
//				Connection conn = ConnectionFactory.getConnection();
//				Statement stmt = conn.createStatement();
//				ResultSet rs = stmt.executeQuery(sql.toString());
//			) {
//			while (rs.next()) {
//				MbtiVO vo = new MbtiVO();
//				vo.setType(rs.getString("TYPE"));
//				vo.setTitle(rs.getString("TITLE"));
//				vo.setLogicalPath(rs.getString("LOGICALPATH"));
//				mbtiList.add(vo);
//			}
//			return mbtiList;
//		} catch (SQLException e) {
//			throw new CustomPersistenceException(e);
//		기존 inMemoryver
		return new ArrayList<MbtiVO>(mbtiMap.values());
		}
//	}
	@Override
	public MbtiVO selectMbti(String type) {
////		DB연결
//		StringBuffer sql = new StringBuffer();
//		sql.append(" SELECT TITLE, LOGICALPATH  ");
//		sql.append(" FROM MBTITABLE ");
//		sql.append(String.format(" WHERE TYPE = '%s' ",type));
//		
//		MbtiVO vo = null; 
//		try (
//			Connection conn = ConnectionFactory.getConnection();
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql.toString());
//		){
//			if(rs.next()){
//				vo = new MbtiVO();
//				vo.setType(rs.getString("TYPE"));
//				vo.setTitle(rs.getString("TITLE"));
//				vo.setLogicalPath(rs.getString("LOGICALPATH"));
//			}
//			return vo;
//		} catch (SQLException e) {
//			throw new CustomPersistenceException(e);
//		기존 inMemoryver
		return mbtiMap.get(type);
		}
//	}
}