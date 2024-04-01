package kr.or.ddit.lecture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.lecture.dao.LectureDAO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.ProfessorVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
	
	private final LectureDAO dao;
	
	@Override
	public List<LectureVO> retrieveLectureListPro(String proNo) {
		return dao.selectLectureListPro(proNo);
	}

	@Override
	public List<LectureVO> retrieveLectureList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectLectureList(paging);
	}

}
