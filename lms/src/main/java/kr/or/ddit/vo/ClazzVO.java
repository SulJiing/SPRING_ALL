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
	
	public ClazzVO(String stdNo, String subCd, Integer clsScore) {
		super();
		this.stdNo = stdNo;
		this.subCd = subCd;
	}
	
	private int rnum;
	private String stdNo;
	private String subCd;
	private Integer clsScore;

}
