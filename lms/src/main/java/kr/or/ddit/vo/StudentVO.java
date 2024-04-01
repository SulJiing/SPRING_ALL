package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "stdNo")
@ToString
@NoArgsConstructor
public class StudentVO implements Serializable{
	
	private int rnum;
	private String stdNo;
	private String stdName;
	private String stdId;
	private String stdTelno;
	private String stdAddress;
	private String proNo;
}
