package kr.or.ddit.lecture.service;

import java.util.List;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.ProfessorVO;

public interface LectureService {
	
	/**
	 * 개설 강의 조회(교수)
	 * @param professor
	 * @return
	 */
	public List<LectureVO> retrieveLectureListPro(String proNo);
	/**
	 * 개설강의목록조회(학생:수강신청)
	 * @param paging
	 * @return
	 */
	public List<LectureVO> retrieveLectureList(PaginationInfo paging);
}
