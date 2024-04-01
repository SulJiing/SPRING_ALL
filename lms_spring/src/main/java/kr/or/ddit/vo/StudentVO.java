package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.constraints.TelNumber;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "stdNo")
@ToString
@NoArgsConstructor
public class StudentVO implements Serializable{
	
	//등록:기본, 수정:insert>>
	private int rnum;
	@NotBlank
	private String stdNo;
	@NotBlank(groups = {InsertGroup.class, Default.class})
	private String stdName;
	@NotBlank
	private String stdId;
	@NotBlank(groups = {InsertGroup.class, Default.class})
	@TelNumber
	private String stdTelno;
	@NotBlank(groups = {InsertGroup.class, Default.class})
	private String stdAddress;
	@NotBlank(groups = {InsertGroup.class, Default.class})
	private String proNo;
	
	//has a //지도교수
	private ProfessorVO professor;
	//has many //수강과목 및 학점
	private List<ClazzVO> clazzList;
	//has a //과목당 하나의 학점을 가지고 있음.
	private ClazzVO clazz;
	
}
