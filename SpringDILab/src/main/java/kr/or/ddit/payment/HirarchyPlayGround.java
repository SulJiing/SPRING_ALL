package kr.or.ddit.payment;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.payment.controller.ReceiveCommandController;
import kr.or.ddit.payment.dao.SalaryDAO;
import kr.or.ddit.payment.service.PaymentService;
import kr.or.ddit.payment.view.PayMonthlyView;

public class HirarchyPlayGround {
	public static void main(String[] args) {
		ConfigurableApplicationContext parent = 
//				new ClassPathXmlApplicationContext("kr/or/ddit/payment/conf/hirarchy/parent-context.xml");
				new AnnotationConfigApplicationContext(PaymentParentJavaConfiguration.class);
		parent.registerShutdownHook();
		
		ConfigurableApplicationContext child = 
				new ClassPathXmlApplicationContext(new String[] {
						"kr/or/ddit/payment/conf/hirarchy/child-context.xml"
				}, parent);
		child.registerShutdownHook();

		child.getBean(ReceiveCommandController.class);
		child.getBean(PaymentService.class);
		child.getBean(PayMonthlyView.class);
		child.getBean(SalaryDAO.class);
		
		parent.getBean(PaymentService.class);
//		parent.getBean(ReceiveCommandController.class);
//		parent.getBean(PayMonthlyView.class);
	}
}
