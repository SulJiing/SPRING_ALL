package kr.or.ddit.student.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.StudentVO;

@Mapper
public interface StudentDAO {
	public int insertStudent(StudentVO student);
	public StudentVO selectStudent(String stdNo);
	public int selectTotalRecord(PaginationInfo paging);
	public List<StudentVO> selectStudentList(PaginationInfo paging);
	public int updateStudent(StudentVO student);
}