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
@EqualsAndHashCode(of = {"proNo", "subCd"})
@ToString
@NoArgsConstructor
public class LectureVO implements Serializable{
	
	private int rnum;
	private String proNo;
	private String subCd;
	private String lecTime;
	private String lecRoom;
	private String lecDay;
	
	//has a //과목이름
	private SubjectVO subject;
	//has a //교수이름
	private ProfessorVO professor;

}
