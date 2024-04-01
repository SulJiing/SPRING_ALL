package kr.or.ddit.case8.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class InfoVO implements Serializable{
//	transient : 직렬화에서 제외
	@JsonIgnore // 마샬링에서 제외
	private transient String info1;
	private String info2;
}
