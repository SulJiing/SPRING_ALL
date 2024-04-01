package kr.or.ddit.lecture.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.ProfessorVO;

@Mapper
public interface LectureDAO {
	
	/**
	 * 개설 강의 조회(교수) : lecture + subject
	 * @param professor
	 * @return
	 */
	public List<LectureVO> selectLectureListPro(String proNo);
	/**
	 * 개설강의목록조회(학생:수강신청) : lecture + subject + professor
	 * @param paging
	 * @return
	 */
	public List<LectureVO> selectLectureList(PaginationInfo paging);
	public int selectTotalRecord(PaginationInfo paging);
}
