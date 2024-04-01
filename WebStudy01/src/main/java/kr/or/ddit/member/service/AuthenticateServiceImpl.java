package kr.or.ddit.member.service;

import kr.or.ddit.member.dao.InMemoryMemberDAOImpl;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {
//	MemberDAO와 의존관계 형성
	private MemberDAO dao = new InMemoryMemberDAOImpl();

	@Override
	public boolean authenticate(MemberVO inputData) {
		boolean auth = false;
		MemberVO saved = dao.selectMemberForAuth(inputData);
		// Id가 없으면 nullpoint가 생기기때문에 미리 한번 걸러줌
		if(saved!=null) {
			String inputPass = inputData.getMemPass();
			String savedPass = saved.getMemPass();
			auth = savedPass.equals(inputPass);
		}
		return auth;
	}
}