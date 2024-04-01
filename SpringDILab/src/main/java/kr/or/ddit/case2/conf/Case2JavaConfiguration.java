package kr.or.ddit.case2.conf;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
@ComponentScan("kr.or.ddit.case2")
public class Case2JavaConfiguration {
	
	@Bean
	@Scope("prototype")
	public LocalDateTime now() {
		return LocalDateTime.now();
	}
}