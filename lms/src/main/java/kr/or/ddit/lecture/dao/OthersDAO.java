package kr.or.ddit.lecture.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 동적 UI 생성에 필요한 데이터 조회 Persistence layer
 * - 교과목 ...
 * 
 */
@Mapper
public interface OthersDAO {
	
	public Map<String, Object> selectSubject();
	public List<Map<String, Object>> selectSubjectList();
	
}
