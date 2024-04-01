package kr.or.ddit.case2.material;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Scope("prototype")
@Slf4j
public class Bibitan implements Gun {
	
	public Bibitan() {
		log.info("{} 객체 생성됐음.",this.getClass().getSimpleName());
	}

	@Override
	public void attack() {
		log.info("{} 로 공격해서 -10 데미지 입혔음.",this.getClass().getSimpleName());
	}
}