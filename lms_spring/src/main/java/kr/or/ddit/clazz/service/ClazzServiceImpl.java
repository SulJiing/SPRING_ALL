package kr.or.ddit.clazz.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.clazz.dao.ClazzDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClazzVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClazzServiceImpl implements ClazzService {
	
	private final ClazzDAO dao;
	
	@Override
	public ServiceResult createClazz(ClazzVO clazz) {
		if(dao.selectClazz(clazz) != null) {
			return ServiceResult.PKDUPLICATED;
		}else {
			return dao.insertClazz(clazz) > 0 ? ServiceResult.OK:ServiceResult.FAIL;
		}
	}

	@Override
	public ServiceResult modifyClazz(ClazzVO clazz) {
		return dao.updateClazz(clazz) > 0 ? ServiceResult.OK:ServiceResult.FAIL;
	}

}
