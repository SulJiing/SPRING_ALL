package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;

public interface BuyerService {
	
	/**
	 * 제조사 추가
	 * @param buyer
	 * @return
	 */
	public ServiceResult createBuyer(BuyerVO buyer);
	
	
	/**
	 * 제조사 수정
	 * @param buyer
	 * @return
	 */
	public ServiceResult modifyBuyer(BuyerVO buyer);
	
	/**
	 * 제조사 상세 조회
	 * @param buyerId
	 * @return 없으면, 예외 발생
	 */
	public BuyerVO retrieveBuyer(String buyerId);
	
	/**
	 * 회원 목록 조회(관리자 용)
	 * totalRecord와 페이징 처리 결과 데이터 목록 조회
	 * @param paging TODO
	 * @return
	 */
	public List<BuyerVO> retrieveBuyerList(PaginationInfo paging);
}
