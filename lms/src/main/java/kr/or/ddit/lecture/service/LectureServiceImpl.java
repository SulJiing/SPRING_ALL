package kr.or.ddit.lecture.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.PKNotFoundException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.lecture.dao.LectureDAO;
import kr.or.ddit.vo.LectureVO;

@Service
public class LectureServiceImpl implements LectureService {
	
	@Inject
	LectureDAO dao;
	
	@Override
	public List<LectureVO> retrieveLectureList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectLectureList(paging);
	}

	@Override
	public List<LectureVO> retrieveLecture(String proNo, String subCd) {
		List<LectureVO> lecture = dao.selectLecture(proNo, subCd);
		if(lecture ==null) 
			throw new PKNotFoundException(String.format("%s,%s 에 해당하는 과목번호 또는 교과목 번호가 없음",proNo,subCd ));
		
		return lecture;
	}

}
