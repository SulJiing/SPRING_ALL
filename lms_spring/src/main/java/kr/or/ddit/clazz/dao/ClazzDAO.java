package kr.or.ddit.clazz.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.ClazzVO;

@Mapper
public interface ClazzDAO {
	
	/**
	 * 수강신청(학생)
	 * @param clazz
	 * @return
	 */
	public int insertClazz(ClazzVO clazz);
	/**
	 * 학점등록(교수)
	 * @param clazz
	 * @return
	 */
	public int updateClazz(ClazzVO clazz);
	/**
	 * 수강신청 했는지 확인
	 * @param clazz
	 * @return
	 */
	public ClazzVO selectClazz(ClazzVO clazz);
	
	
}
