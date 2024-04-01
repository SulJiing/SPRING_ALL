package kr.or.ddit.student.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.student.dao.StudentOthersDAO;

@Service
public class StudentOthersServiceImpl implements StudentOthersService {
	
	@Inject
	private StudentOthersDAO othersDao;
	
	@Override
	public List<Map<String, Object>> retrieveStudentList(PaginationInfo paging) {
		int totalRecord = othersDao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return othersDao.selectStudentList(paging);
	}

	@Override
	public Map<String, Object> retrieveStudent(String stdNo) {
		return othersDao.selectStudent(stdNo);
	}

	@Override
	public List<Map<String, Object>> retrieveStudentClazz(String stdNo) {
		return othersDao.selectStudentClazz(stdNo);
	}

}
