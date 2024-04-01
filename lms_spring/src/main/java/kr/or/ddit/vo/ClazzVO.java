package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode (of = {"stdNo", "subCd"})
@ToString
@NoArgsConstructor
public class ClazzVO implements Serializable{
	
	private int rnum;
	private String stdNo;
	private String subCd;
	private Integer clsScore;
	
	//has a //과목 이름
	private SubjectVO subject;
	//has a //학생 이름
	private StudentVO student;

}
