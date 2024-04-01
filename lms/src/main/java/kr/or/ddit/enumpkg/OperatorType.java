package kr.or.ddit.enumpkg;

public enum OperatorType {
	PLUS('+',(l,r)->l+r), 
	MINUS('-',(l,r)->l-r), 
	MULTIPLY('*',(l,r)->l*r), 
	DIVIDE('/',new BiOperandOperator() {
		public double operate(double leftOp, double rightOp) {
			return leftOp / rightOp;
		}
	}); //익명 클래스 활용(자바 8보다 버전이 낮다면 : 그래서 람다식을 못쓴다면)
	
	private OperatorType(char sign, BiOperandOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	private BiOperandOperator realOperator;
	private char sign;
	
	public char getSign() {
		return sign;
	}
	
	//사칙연산 결과값(각자의 위에 정의된 식으로 연산)
	public double operate(double leftOp, double rightOp) {
		return realOperator.operate(leftOp, rightOp);
	}
	
	public String getExpression(double leftOp, double rightOp) {
		return String.format("%f %c %f = %f", leftOp, sign, rightOp, operate(leftOp, rightOp));
	}
	
	//행위는 없고 어떻게 실행할지만 있음
	@FunctionalInterface  //자바8부터 생략 가능 //람다식으로 구체화 가능
	public static interface BiOperandOperator{
		public double operate(double leftOp, double rightOp);
	}
	
}
