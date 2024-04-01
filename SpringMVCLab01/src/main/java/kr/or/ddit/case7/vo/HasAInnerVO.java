package kr.or.ddit.case7.vo;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class HasAInnerVO {
	private String innerParam1;
	@Min(1)
	private int innerParam2;
	
	private List<@NotNull HasManyInnerVO> innerList; 
}
