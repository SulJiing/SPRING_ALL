package kr.or.ddit.autodi;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case1.Foo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutoDIPlayGround {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/autodi/conf/autoDI-context.xml");
		context.registerShutdownHook();
		
		// 현재 컨테이너에 몇개의 bean이 등록되어 있는 지 확인
		log.info("bean 갯수 : {} ",context.getBeanDefinitionCount());
		Arrays.stream(context.getBeanDefinitionNames())
			.forEach(n->log.info("id : {}",n));
		
		Foo foo = context.getBean(Foo.class);
		log.info("주입된 결과 : {}",foo);
	}
}
