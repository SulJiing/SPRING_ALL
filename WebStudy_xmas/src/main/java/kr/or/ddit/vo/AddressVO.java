package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AddressVO {
	private int adrsNo;
	private String adrsOwner;
	private String adrsName;
	private String adrsHp;
	private String adrsZip;
	private String adrsAdd1;
	private String adrsAdd2;
}
