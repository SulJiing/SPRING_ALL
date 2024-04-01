package kr.or.ddit.case1.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.case1.Bar;
import kr.or.ddit.case1.Baz;
import kr.or.ddit.case1.Foo;

// Beans의 역할
@Lazy
@ComponentScan("kr.or.ddit.case1")
@Configuration
public class Case1JavaConfiguration {
	/*
	@Bean
	@Scope("prototype")
	public Baz baz() {
		return new Baz();
	}
	
	@Bean
	@Lazy(true) // 얘는 기본값이 true
	public Bar bar() {
		return new Bar();
	}
	
	@Bean
	public Foo foo(Bar bar) {
		return new Foo(bar);
	}
	*/
}