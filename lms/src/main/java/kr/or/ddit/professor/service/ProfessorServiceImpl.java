package kr.or.ddit.professor.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.PKNotFoundException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.professor.dao.ProfessorDAO;
import kr.or.ddit.vo.ProfessorVO;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	@Inject
	ProfessorDAO dao;

	@Override
	public ServiceResult createProfessor(ProfessorVO professor) {
		ServiceResult result = null;
		result = dao.insertProfessor(professor) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		return result;
	}

	@Override
	public List<ProfessorVO> retrieveProfessorList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectProfessorList(paging);
	}

	@Override
	public ProfessorVO retrieveProfessor(String proNo) {
		ProfessorVO professor = dao.selectProfessor(proNo);
		 if (professor == null) {
		        throw new PKNotFoundException(proNo + "에 해당하는 사용자 없음.");
		    }
		return professor;
	}

	@Override
	public ServiceResult modifyProfessor(ProfessorVO professor) {
		ServiceResult result = null;
		result =  dao.updateProfessor(professor) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		return result;
	}
}