package kr.or.ddit.lecture.service;

import java.util.List;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.LectureVO;

public interface LectureService {
	
	/**
	 * 강의 목록 조회
	 * totalRecode와 페이징 처리 결과 데이터 목록 조회
	 * @param paging
	 * @return
	 */
	public List<LectureVO> retrieveLectureList(PaginationInfo paging);
	
	
	
	/**
	 * 강의 목록 조회
	 * @param proNo 
	 * @param subCds
	 * @return
	 */
	public List<LectureVO> retrieveLecture(String proNo, String subCds);
}
