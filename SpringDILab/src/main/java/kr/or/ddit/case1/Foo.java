package kr.or.ddit.case1;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Repository
@Data
@Slf4j
public class Foo {
	// 필수 전략
	private Bar bar;
	
//	@Autowired // 관계 형성(setter)
	// 옵셔널
	private Baz baz;
	
	public Foo(Bar bar) {
		super();
		this.bar = bar;
	}
	
//	@Resource(name = "baz")
	@Inject
	@Named("baz") // 세트, Resource와 같음
	@Required
	public void setBaz(Baz baz) {
		this.baz = baz;
	}
	
	@PostConstruct // 객체 생성 후 주입 후
	public void init123() {
		log.info("주입된 결과 ----------> {}, {}",bar,baz);
	}
}