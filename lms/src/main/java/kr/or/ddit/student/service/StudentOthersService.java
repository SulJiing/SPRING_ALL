package kr.or.ddit.student.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.paging.PaginationInfo;

public interface StudentOthersService {
	public List<Map<String, Object>> retrieveStudentList (PaginationInfo paging);
	public Map<String, Object> retrieveStudent (String stdNo);
	public List<Map<String, Object>> retrieveStudentClazz(String stdNo);
}
