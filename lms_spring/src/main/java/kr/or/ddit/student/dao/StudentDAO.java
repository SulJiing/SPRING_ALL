package kr.or.ddit.student.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.StudentVO;

@Mapper
public interface StudentDAO {
	
	
	/**
	 * 학생목록조회(교직원) : student
	 * @param paging
	 * @return
	 */
	public List<StudentVO> selectStudentList(PaginationInfo paging);
	public int selectTotalRecord(PaginationInfo paging);
	/**
	 * 학생상세조회(교직원) : student + class + subject + professor(지도교수)
	 * @param stdNo
	 * @return
	 */
	public StudentVO selectStudent(String stdNo);
	/**
	 * 과목을 수강하는 학생 조회(교수) : student + class
	 * @param subCd
	 * @return
	 */
	public List<StudentVO> selectStudentSubList(String subCd);
	
	/**
	 * 학생등록(교직원)
	 * @param student
	 * @return
	 */
	public int insertStudent(StudentVO student);
	/**
	 * 학생수정(교직원)
	 * @param student
	 * @return
	 */
	public int updateStudent(StudentVO student);
	
}
