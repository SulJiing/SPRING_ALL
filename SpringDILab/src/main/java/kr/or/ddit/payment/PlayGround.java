package kr.or.ddit.payment;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.payment.controller.ReceiveCommandController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayGround {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/or/ddit/payment/conf/payment-autodi.xml");
		
		// 어플리케이션이 종료될 때 같이 종료되도록 만드는 조건
		context.registerShutdownHook();
		
		ReceiveCommandController controller = context.getBean(ReceiveCommandController.class);
		String empCode = "b001";
		String payStub = controller.receiveCommand(empCode);
		log.info("급여 명세서 : {}",payStub);
	}
}