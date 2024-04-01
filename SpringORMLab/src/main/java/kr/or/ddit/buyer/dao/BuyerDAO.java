package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;

@Mapper
public interface BuyerDAO {
	
	/**
	 * 제조사 추가
	 * @param buyer
	 * @return
	 */
	public int insertBuyer(BuyerVO buyer);
	
	/**
	 * 제조사 수정
	 * @param buyerId
	 * @return
	 */
	public int updateBuyer(BuyerVO buyer);
	
	/**
	 * 제조사 조회
	 * @param buyerId
	 * @return 없으면, null 반환
	 */
	public BuyerVO selectBuyer(String buyerId);
	
	/**
	 * 제조사 목록 조회, 검색과 페이징 적용
	 * @param paging TODO
	 * @return 존재하지 않는 경우, list.size()==0
	 */
	public List<BuyerVO> selectBuyerList(PaginationInfo paging);
	
	/**
	 * 전체 제조사의 수를 조회 (totalRecord)
	 * @param paging
	 * @return
	 */
	public int selectTotalRecord(PaginationInfo paging);
}
