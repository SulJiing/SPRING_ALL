package kr.or.ddit.enumpkg;

public enum OperatorType {
	PLUS('+',(l,r)->l+r),
	MINUS('-',(l,r)->l-r), 
	MULTIPLY('*',(l,r)->l*r), 
	DIVIDE('/',new BiOprandOperator() { // 익명 클래스
		public double operate(double leftOp, double rightOp) {
			return leftOp / rightOp;
		}
	});
	
	private OperatorType(char sign, BiOprandOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	private BiOprandOperator realOperator;
	private char sign;
	
	public char getSign() {
		return sign;
	}
	
	public double operate(double leftOp, double rightOp) {
		return realOperator.operate(leftOp, rightOp);
	}
	// view 텍스트 줄이기 위한 메서드 - 너무 어려운데 굳이 해야되나?
	public String getExpression(double leftOp, double rightOp) {
		return String.format("%f %c %f = %f",leftOp, sign, rightOp, operate(leftOp,rightOp));
	}
	
	@FunctionalInterface // 생략가능 - 람다식으로 표현이 가능
	public static interface BiOprandOperator{
		// double 타입으로 파리미터 2개를 받는 인터페이스
		public double operate(double leftOp, double rightOp);
	}
}
