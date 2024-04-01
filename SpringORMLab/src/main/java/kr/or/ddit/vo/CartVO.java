package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of= {"cartNo","cartProd"})
public class CartVO {
	 private String cartMember;
	 private String cartNo;
	 private String cartProd;
	 private String cartQty;
	 // Has A 관계
	 private ProdVO prod;
}
