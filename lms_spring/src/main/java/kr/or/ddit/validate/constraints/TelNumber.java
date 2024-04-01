package kr.or.ddit.validate.constraints;

import static java.lang.annotation.ElementType.*; //저기있는 곳의 static맴버는 미리 임포트 해놓겠다.
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.groups.Default;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = TelNumberValidator.class)
public @interface TelNumber {
	String regex() default "\\d{3}-\\d{3,4}-\\d{4}"; //원하면 정규식을 수정할 수 있게 한거임
	String message() default "{kr.or.ddit.validate.constraints.TelNumber}";
								//메세지번들에서 사용할 키로 사용한다는 뜻{}
	Class<?>[] groups() default {}; //기본그룹: 생략시 이렇게됨  //속성이 될려면 () 이거를 해야함.
	Class<?>[] payload() default {};
}
