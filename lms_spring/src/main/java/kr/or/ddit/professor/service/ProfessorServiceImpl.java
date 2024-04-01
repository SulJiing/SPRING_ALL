package kr.or.ddit.professor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.professor.dao.ProfessorDAO;
import kr.or.ddit.vo.ProfessorVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {
	
	private final ProfessorDAO dao;
	
	@Override
	public List<ProfessorVO> retrieveProfessorList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectProfessorList(paging);
	}

	@Override
	public ProfessorVO retrieveProfessor(String proNo) {
		return dao.selectProfessor(proNo);
	}

	@Override
	public ServiceResult insertProfessor(ProfessorVO professor) {
		if(retrieveProfessor(professor.getProNo()) != null) {
			return ServiceResult.PKDUPLICATED;
		}else {
			return dao.insertProfessor(professor) > 0 ? ServiceResult.OK:ServiceResult.FAIL;
		}
	}

	@Override
	public ServiceResult updateProfessor(ProfessorVO professor) {
		return dao.updateProfessor(professor) > 0 ? ServiceResult.OK:ServiceResult.FAIL;
	}

}
