package kr.or.ddit.student.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.paging.PaginationInfo;

@Mapper
public interface StudentOthersDAO {
	public int selectTotalRecord(PaginationInfo paging);
	public List<Map<String, Object>> selectStudentList(PaginationInfo paging);
	public Map<String, Object> selectStudent (String stdNo);
	public List<Map<String, Object>> selectStudentClazz (String stdNo);
}
