package kr.or.ddit.resource;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import lombok.extern.slf4j.Slf4j;

/**
 *	{@link Resource} : 모든 종류의 자원에 대한 접근 방법을 추상화시켜놓은 타입.
 *	{@link ResourceLoader} : 자원을 검색하고 메모리에 로딩하는 객체
 *	1. 파일시스템 자원 : D:/01.medias/image/cry.jpg
 *	2. 클래스패스 자원 : /SpringDILab/src/main/resources/(여기까지 ClassPath) kr/or/ddit/db/dbInfo.properties
 *	3. 웹 자원	  : https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png
 */
@Slf4j
public class ResourceLoaderDesc {
	public static void main(String[] args) throws MalformedURLException {
/*		// 물리주소
		File fileSystemFile = new File("D:/01.medias/image/cry.jpg");
		
		// 논리주소라서 찾지못함 -> 물리주소로 만들어야 함
//		File classpathFile = new File("kr/or/ddit/db/dbInfo.properties");
		String realPath = ResourceLoaderDesc.class.getResource("/kr/or/ddit/db/dbInfo.properties").getFile();
		File classpathFile = new File(realPath);
		
		// 실제로 없는 주소라 주소를 찾아와야 하기 때문에 복잡한 구조가 필요해짐
		URL url = new URL("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
*/
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/or/ddit/resource/conf/resource-context.xml");
		
		context.registerShutdownHook();
		
		Resource fsRes = context.getResource("file:D:/01.medias/image/cry.jpg");
		log.info("file system Resource : {}", fsRes);
		Resource cpRes = context.getResource("classpath:kr/or/ddit/db/dbInfo.properties");
		log.info("classpath Resource : {}", cpRes);
		// https 자체가 prefix의 역할
		Resource webRes = context.getResource("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		log.info("web Resource : {}", webRes);
		
		VariousVO vo = context.getBean(VariousVO.class);
		log.info("주입결과 : {}",vo);
		
		OtherVariousVO others = context.getBean(OtherVariousVO.class);
		log.info("주입결과 : {}",others);
	}
}