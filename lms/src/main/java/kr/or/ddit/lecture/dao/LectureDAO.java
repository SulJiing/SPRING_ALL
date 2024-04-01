package kr.or.ddit.lecture.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.LectureVO;

@Mapper
public interface LectureDAO {
	
	/**
	 * 강의를 조회하는 메서드
	 * @param proNo 교수의 식별 번호(P.K)
	 * @param subCds 교과목 번호
	 * @return LectureVO
	 */
	public List<LectureVO> selectLecture(@Param("proNo") String proNo,@Param("subCd") String subCd);
	
	
	/**
	 * 차후 페이징 처리를 위한 메서드
	 * @param paging
	 * @return
	 */
	public int selectTotalRecord(PaginationInfo paging);
	
	
	public List<LectureVO> selectLectureList(PaginationInfo paging);
	
	
}
