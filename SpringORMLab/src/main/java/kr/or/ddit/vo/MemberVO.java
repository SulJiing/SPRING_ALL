package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.constraints.TelNumber;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *	회원 관리용 Domain Layer
 */
@Data
@EqualsAndHashCode(of = "memId")
@NoArgsConstructor
public class MemberVO implements Serializable {
	
	public MemberVO(String memId, String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}
	private int rnum;
//	hibernate validation 사용 - 주의사항으로 hibernate를 사용하면 안됨(인터페이스를 사용해야하기 때문) -> validation
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	private String memId;
	
	@NotBlank
	@Size(min = 4, max = 12)
	private String memPass;
	
	@NotBlank(groups = InsertGroup.class) // 그룹 지정 거기에서만 사용하도록
	private String memName;
	
	@JsonIgnore
	@ToString.Exclude
	@Size(min = 6, max = 6)
	private transient String memRegno1;
	
	@JsonIgnore
	@ToString.Exclude
	private transient String memRegno2;
	
	private LocalDateTime memBir;
	
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	
	@TelNumber(regex = "\\d{2,3}\\)\\d{3,4}-\\d{4}") // 커스텀 어노테이션
	private String memHometel;
	
	@TelNumber // 커스텀 어노테이션
	private String memComtel;
	
	@NotBlank
	@TelNumber // 커스텀 어노테이션
	private String memHp;
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private LocalDate memMemorialday;
	private Integer memMileage;
	private boolean memDelete;
	// Has Many 관계
	private List<CartVO> cartList;
	// MEMBER(1) : CART[PROD(1)](N)
	
	private String memRole;
}