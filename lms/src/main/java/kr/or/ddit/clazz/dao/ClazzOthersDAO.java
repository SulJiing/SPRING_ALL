package kr.or.ddit.clazz.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.ClazzVO;

@Mapper
public interface ClazzOthersDAO {
	
	public List<Map<String, Object>> selectStudentClazz (String subCd);
	public int updateClazz(ClazzVO clazz);
	
}
