package kr.or.ddit.dummy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import kr.or.ddit.vo.CartVO;

@Mapper
public interface DummyDAO {
	public List<CartVO> selectCartList();
}
