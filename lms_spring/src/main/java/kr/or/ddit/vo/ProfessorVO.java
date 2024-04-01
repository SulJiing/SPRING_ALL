package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.constraints.TelNumber;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "proNo")
@ToString
@NoArgsConstructor
public class ProfessorVO implements Serializable{
	
	//등록:기본, 수정:update>>
	
	private int rnum;
	@NotBlank
	private String proNo;
	@NotBlank(groups = {UpdateGroup.class, Default.class})
	private String proName;
	@NotBlank(groups = {UpdateGroup.class, Default.class})
	private String proMajor;
	@NotBlank(groups = {UpdateGroup.class, Default.class})
	@TelNumber
	private String proTelno;
	
	//has many
	private List<LectureVO> lectureList;

}
