package kr.or.ddit.address.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AddressVO;

public interface AddressService {
	/**
	 * 주소록 등록 처리
	 * @param member
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult createMember(AddressVO address);
	/**
	 * 주소록 상세 조회
	 * @param memId 조회할 회원의 promary key
	 * @return 존재하지 않는 경우, {@link PKNotFoundException}
	 */
	public AddressVO retrieveMember(String adrsName);
	/**
	 * 주소록 조회(관리자 용)
	 * @return
	 */
	public List<AddressVO> retrieveMemberList();
	/**
	 * 기존 주소록 수정
	 * @param member
	 * @return INVALIDPASSWORD, OK, FAIL 
	 */
	public ServiceResult modifyMember(AddressVO address);
	/**
	 * 주소록 삭제
	 * @param member
	 * @return INVALIDPASSWORD, OK, FAIL 
	 */
	public ServiceResult removeMember(AddressVO adrsName);

}
