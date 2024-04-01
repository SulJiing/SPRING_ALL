package kr.or.ddit.case2;

import java.time.LocalDateTime;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case2.material.Bibitan;
import kr.or.ddit.case2.material.Gun;
import kr.or.ddit.case2.material.Hunter;
import kr.or.ddit.case2.material.ShotGun;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HuntingGround {
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context =
//				new ClassPathXmlApplicationContext("kr/or/ddit/case2/conf/case2_context2.xml");
				new GenericXmlApplicationContext("classpath:kr/or/ddit/case2/conf/case2_context2.xml");
		
		context.registerShutdownHook();
		
		Hunter hunter = context.getBean(Hunter.class);
		
		Gun shotGun = context.getBean(ShotGun.class);
		Gun bibitan = context.getBean(Bibitan.class);
		LocalDateTime now = context.getBean(LocalDateTime.class);
		
		log.info("공격시점 : {}",now);
		hunter.hunting();
		
		Thread.sleep(1000);
		now = context.getBean(LocalDateTime.class);
		log.info("공격시점 : {}",now);
		hunter.setGun(bibitan);
		hunter.hunting();
		
		Thread.sleep(1000);
		now = context.getBean(LocalDateTime.class);
		log.info("공격시점 : {}",now);
		hunter.setGun(shotGun);
		hunter.hunting();
	}
}
