package kr.or.ddit.address.dao;

import java.util.List;

import kr.or.ddit.vo.AddressVO;

public interface AddressDAO {
	/**
	 * 주소록 전체 조회
	 * @return
	 */
	public List<AddressVO> selectAllAddress();
	
	/**
	 * 한명 상세 조회
	 * @param adrsOwner
	 * @return
	 */
	public AddressVO selectAddress(String adrsName);

	/**
	 * 새로운 주소록 추가
	 * @param address
	 * @return
	 */
	public int insertAddress(AddressVO address);
	
	/**
	 * 기존 주소록 수정
	 * @param address
	 * @return
	 */
	public int updateAddress(AddressVO address);
	
	/**
	 * 주소록 삭제
	 * @param adrsName
	 * @return
	 */
	public int deleteAddress(String adrsName);
}
