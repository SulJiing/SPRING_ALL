package kr.or.ddit.professor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.ProfessorVO;

@Mapper
public interface ProfessorDAO {
	
	/**
	 * 교수목록조회(교직원)
	 * @param paging
	 * @return
	 */
	public List<ProfessorVO> selectProfessorList(PaginationInfo paging);
	public int selectTotalRecord(PaginationInfo paging);
	/**
	 * 교수상세조회(교직원) : professor + lecture + subject
	 * @param proNo
	 * @return
	 */
	public ProfessorVO selectProfessor(String proNo);
	/**
	 * 교수등록(교직원)
	 * @param professor
	 * @return
	 */
	public int insertProfessor(ProfessorVO professor);
	/**
	 * 교수수정(교직원)
	 * @param professor
	 * @return
	 */
	public int updateProfessor(ProfessorVO professor);
	
	
}
