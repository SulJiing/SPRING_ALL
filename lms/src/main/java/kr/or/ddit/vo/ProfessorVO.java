package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "proNo")
@ToString
@NoArgsConstructor
public class ProfessorVO implements Serializable{
	
	private int rnum;
	private String proNo;
	private String proName;
	private String proMajor;
	private String proTelno;

	private String subCd;
	private String lecTime;
	private String lecRoom;
	private String lecDay;

}
