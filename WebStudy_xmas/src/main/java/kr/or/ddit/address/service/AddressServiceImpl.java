package kr.or.ddit.address.service;

import java.util.List;

import kr.or.ddit.address.dao.AddressDAO;
import kr.or.ddit.address.dao.AddressDAOImpl;
import kr.or.ddit.common.exception.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AddressVO;
import kr.or.ddit.vo.MemberVO;

public class AddressServiceImpl implements AddressService {
	AddressDAO dao = new AddressDAOImpl();

	@Override
	public ServiceResult createMember(AddressVO address) {
		ServiceResult result = null;
		if(dao.selectAddress(address.getAdrsName())==null) {
			result = dao.insertAddress(address) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		} else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public AddressVO retrieveMember(String adrsName) {
		AddressVO adrs = dao.selectAddress(adrsName);
		if(adrs==null) 
			throw new PKNotFoundException(String.format("%s에 해당하는 주소록 없음.",adrsName));
		return adrs;
	}

	@Override
	public List<AddressVO> retrieveMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyMember(AddressVO address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeMember(AddressVO adrsName) {
		// TODO Auto-generated method stub
		return null;
	}

}
