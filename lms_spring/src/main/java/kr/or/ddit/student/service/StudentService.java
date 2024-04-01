package kr.or.ddit.student.service;

import java.util.List;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.StudentVO;

public interface StudentService {
	
	/**
	 * 학생목록조회(교직원)
	 * @param paging
	 * @return
	 */
	public List<StudentVO> retrieveStudentList(PaginationInfo paging);
	/**
	 * 학생상세조회(교직원)
	 * @param stdNo
	 * @return
	 */
	public StudentVO retrieveStudent(String stdNo);
	public List<StudentVO> retrieveStudentSubList(String subCd);
	/**
	 * 학생등록(교직원)
	 * @param student
	 * @return
	 */
	public ServiceResult insertStudent(StudentVO student);
	/**
	 * 학생수정(교직원)
	 * @param student
	 * @return
	 */
	public ServiceResult updateStudent(StudentVO student);
}
