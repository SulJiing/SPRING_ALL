package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *	하나의 MBTI유형에 대한 정보를 캡슐화한 객체(VO(ValueObject), (Java)Bean, DTO(DataTransferObject), Model, Info 라고도 함)
 *	
 *	자바빈 규약
 *	1. 값을 지정할 수 있는 프로퍼티 제공
 *	2. 프로퍼티에 대한 캡슐화
 *	3. 캡슐화된 프로퍼티에 대해 인터페이스 지원(getter/setter) : get[set]프로퍼티네임의 첫글자를 대문자로한 프로퍼티명(=capitalized property name)
 *	4. 프로퍼티 상태 확인 인터페이스 지원(toString)
 *	5. 객체의 상태를 비교할 수 있는 인터페이스 지원(equals, compareTo)
 *	6. 직렬화 지원
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class MbtiVO implements Serializable{
	
	private String type;
	private String title;
	private String logicalPath;
}
