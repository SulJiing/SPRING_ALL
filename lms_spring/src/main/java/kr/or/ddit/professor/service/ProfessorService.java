package kr.or.ddit.professor.service;

import java.util.List;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ProfessorVO;

public interface ProfessorService {
	
	/**
	 * 교수목록조회(교직원)
	 * @param paging
	 * @return
	 */
	public List<ProfessorVO> retrieveProfessorList(PaginationInfo paging);
	/**
	 * 교수상세조회(교직원)
	 * @param proNo
	 * @return
	 */
	public ProfessorVO retrieveProfessor(String proNo);
	/**
	 * 교수등록(교직원)
	 * @param professor
	 * @return
	 */
	public ServiceResult insertProfessor(ProfessorVO professor);
	/**
	 * 교수수정(교직원)
	 * @param professor
	 * @return
	 */
	public ServiceResult updateProfessor(ProfessorVO professor);
	
	
}
