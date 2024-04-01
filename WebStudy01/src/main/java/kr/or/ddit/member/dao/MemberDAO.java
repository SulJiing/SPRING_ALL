package kr.or.ddit.member.dao;

import kr.or.ddit.vo.MemberVO;

/**
 *	회원 관리용 persistence(지속적인) Layer
 */
public interface MemberDAO {
	/**
	 * 인증처리를 위해 회원정보 조회
	 * @param inputData 사용자가 입력한 정보를 가진 VO
	 * @return persistence한 형태로 저장되어 있던 정보를 가진 VO, 없으면 null 반환
	 */
	public MemberVO selectMemberForAuth(MemberVO inputData);
}
