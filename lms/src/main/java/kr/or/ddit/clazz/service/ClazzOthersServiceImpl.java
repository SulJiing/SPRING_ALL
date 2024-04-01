package kr.or.ddit.clazz.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.clazz.dao.ClazzOthersDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClazzVO;

@Service
public class ClazzOthersServiceImpl implements ClazzOthersService {
	
	@Inject
	ClazzOthersDAO othersDao;
	
	@Override
	public List<Map<String, Object>> retrieveStudentClazz(String subCd) {
		return othersDao.selectStudentClazz(subCd);
	}

	@Override
	public ServiceResult modifyClazz(ClazzVO clazz) {
		return othersDao.updateClazz(clazz) > 0 ? ServiceResult.OK:ServiceResult.FAIL;
	}

}
