package kr.or.ddit.validate.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelNumberValidator implements ConstraintValidator<TelNumber, String> { //만들었던 어노테이션, 어떤 타입을 검증할건지
	
	private TelNumber annotation;
	
	@Override
	public void initialize(TelNumber constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean valid = true;
		if(value != null && value.trim().length() > 0)  //StringUtils.isBlank를 안쓰는 이유는 common의 lang3이 없어도 쓸 수 있게 하려고
			valid = value.matches(annotation.regex()); //TelNumber 속성에서 넣어주었던 값.
		return valid;
	}
}
