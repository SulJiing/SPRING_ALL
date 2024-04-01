package kr.or.ddit.clazz.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClazzVO;

public interface ClazzService {
	
	/**
	 * 수강신청(학생)
	 * @param clazz
	 * @return OK, FAIL, PKDUPLICATED(중복신청)
	 */
	public ServiceResult createClazz(ClazzVO clazz);
	/**
	 * 학점등록(교수)
	 * @param clazz
	 * @return
	 */
	public ServiceResult modifyClazz(ClazzVO clazz);
	
}
