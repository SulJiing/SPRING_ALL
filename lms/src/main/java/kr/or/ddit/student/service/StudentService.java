package kr.or.ddit.student.service;

import java.util.List;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.StudentVO;

public interface StudentService {
	public ServiceResult createStudent(StudentVO student);
	public List<StudentVO> retrieveStudentList(PaginationInfo paging);
	public StudentVO retrieveStudent(String stdNo);
	public ServiceResult modifyStudent(StudentVO student);
}
