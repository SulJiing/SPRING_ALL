package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "subCd")
@ToString
@NoArgsConstructor
public class SubjectVO {
	
	private String subCd;
	private String subGubun;
	private String subName;
	private Integer subCredit;
	
	//has many //수업을 듣는 학생들 조회 가능
	private List<ClazzVO> clazzList;
	
}
