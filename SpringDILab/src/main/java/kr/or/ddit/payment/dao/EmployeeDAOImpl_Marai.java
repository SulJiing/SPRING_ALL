package kr.or.ddit.payment.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("maria")
public class EmployeeDAOImpl_Marai implements EmployeeDAO{
	private static Map<String, StringBuffer> empTB;
	static {
		empTB = new LinkedHashMap<>();
		empTB.put("a001",new StringBuffer("a001, 정민지(mariaDB)"));
		empTB.put("b001",new StringBuffer("b001, 변상원(mariaDB)"));
	}
	@Override
	public StringBuffer selectEmployee(String empCode) {
		return empTB.get(empCode);
	}
}
