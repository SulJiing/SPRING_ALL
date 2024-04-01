package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "id")
@ToString
public class StaffVO implements Serializable{
	
	public StaffVO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	private String id;
	private String pw;
}
