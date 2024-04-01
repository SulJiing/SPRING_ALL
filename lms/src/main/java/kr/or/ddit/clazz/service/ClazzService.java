package kr.or.ddit.clazz.service;

import java.util.List;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClazzVO;

public interface ClazzService {
	public ServiceResult createClazz(ClazzVO clazz);
	public List<ClazzVO> retrieveClazzList(PaginationInfo paging);
	public ClazzVO retrieveClazz(String stdNo, String subCd);
	public ServiceResult modifyClazz(ClazzVO clazz);
}
