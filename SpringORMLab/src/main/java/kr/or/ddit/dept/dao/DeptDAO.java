package kr.or.ddit.dept.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.DeptVO;

@Mapper
public interface DeptDAO {
	
	public int selectTotalRecord(PaginationInfo paging);
	
	public List<DeptVO> selectDeptList(PaginationInfo paging);
}
