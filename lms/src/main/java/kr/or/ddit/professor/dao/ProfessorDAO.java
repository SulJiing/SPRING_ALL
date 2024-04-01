package kr.or.ddit.professor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.ProfessorVO;

@Mapper
public interface ProfessorDAO {
	
	public int insertProfessor(ProfessorVO professor);
	
	public ProfessorVO selectProfessor(String proNo);
	
	public int selectTotalRecord(PaginationInfo paging);
	
	public List<ProfessorVO> selectProfessorList(PaginationInfo paging);
	
	public int updateProfessor(ProfessorVO professor);
}
