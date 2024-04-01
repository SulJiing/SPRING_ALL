package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "deptno")
public class DeptVO implements Serializable{
	private String deptno;
	private String dname;
	private String loc;
}
