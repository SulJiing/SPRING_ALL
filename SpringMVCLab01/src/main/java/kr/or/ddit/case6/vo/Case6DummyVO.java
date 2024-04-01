package kr.or.ddit.case6.vo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class Case6DummyVO {
	private String[] param1;
	private int paramA;
	private boolean param3;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate date;
}
