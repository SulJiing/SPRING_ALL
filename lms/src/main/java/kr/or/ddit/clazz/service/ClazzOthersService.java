package kr.or.ddit.clazz.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClazzVO;

public interface ClazzOthersService {
	
	public List<Map<String, Object>> retrieveStudentClazz(String subCd);
	public ServiceResult modifyClazz(ClazzVO clazz);
}
