package kr.or.ddit.payment.dao;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDAOFactory {
	public static EmployeeDAO getEmployeedDAO() {
		return new EmployeeDAOImpl_Oracle();
	}
}
