package kr.or.ddit.clazz.dao;

import java.util.List;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.ClazzVO;

public interface ClazzDAO {
	public int insertClazz(ClazzVO clazz);
	public ClazzVO selectClazz(String stdNo, String subCd);
	public int selectTotalRecord(PaginationInfo paging);
	public List<ClazzVO> selectClazzList(PaginationInfo paging);
	public int updateClazz(ClazzVO clazz);
}
