package kr.or.ddit.student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.student.dao.StudentDAO;
import kr.or.ddit.vo.StudentVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	private final StudentDAO dao;
	
	@Override
	public List<StudentVO> retrieveStudentList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectStudentList(paging);
	}

	@Override
	public StudentVO retrieveStudent(String stdNo) {
		return dao.selectStudent(stdNo);
	}
	
	@Override
	public List<StudentVO> retrieveStudentSubList(String subCd) {
		return dao.selectStudentSubList(subCd);
	}

	@Override
	public ServiceResult insertStudent(StudentVO student) {
		if(retrieveStudent(student.getStdNo()) != null) {
			return ServiceResult.PKDUPLICATED;
		}else {
			return dao.insertStudent(student) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}
	}
  
	@Override
	public ServiceResult updateStudent(StudentVO student) {
		return dao.updateStudent(student) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
