package kr.or.ddit.professor.service;

import java.util.List;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ProfessorVO;

public interface ProfessorService {
	public ServiceResult createProfessor(ProfessorVO professor);
	public List<ProfessorVO> retrieveProfessorList(PaginationInfo paging);
	public ProfessorVO retrieveProfessor(String proNo);
	public ServiceResult modifyProfessor(ProfessorVO professor);
}
