package kr.or.ddit.case7.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

/**
 *	command object 활용할 객체
 */
@Data
public class SampleVO {
	@NotBlank
	private String strParam;
	@Min(1)
	private int numParam;
	private char chParam;
	@DateTimeFormat(iso = ISO.DATE) // 패턴지정(연4-월2-일2)
	private LocalDate dateParam;
	@DateTimeFormat(iso = ISO.DATE_TIME) // 패턴지정
	private LocalDateTime dateTimeParam;
	
	private Integer optionParam;
	
	// has a 관계
	@NotNull // 단순히 객체가 존재해야 함을 검증
	@Valid // 타입에 들어가서 검증을 진행함
	private HasAInnerVO inner; 
	
//	// has many 관계
//	@NotEmpty // size가 0이면 안됨을 검증
//	@Valid // 타입에 들어가서 검증을 진행함
//	private List<@NotNull HasManyInnerVO> innerList; 
}
