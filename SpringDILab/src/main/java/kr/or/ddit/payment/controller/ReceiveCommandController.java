package kr.or.ddit.payment.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import kr.or.ddit.payment.service.PaymentService;
import kr.or.ddit.payment.view.PayMonthlyView;

@Controller
public class ReceiveCommandController {
//	@Inject
	private PaymentService service;
//	@Inject
	private PayMonthlyView view;
	
	// 생성자
	public ReceiveCommandController(PaymentService service, PayMonthlyView view) {
		super();
		this.service = service;
		this.view = view;
	}

	public String receiveCommand(String empCode) {
		StringBuffer model = service.payMonthly(empCode);
		return view.generateContent(model);
	}
}
