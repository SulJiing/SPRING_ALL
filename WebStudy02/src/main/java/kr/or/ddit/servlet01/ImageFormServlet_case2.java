package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *  Java 8의 새로운 문법
 *	1. Functional Interface : 인터페이스의 추상 메서드가 하나인 경우. -> lambda 표현식으로 연결됨.
 *	2. lambda : (함수형 인터페이스의 대표 메서드 인자들...) -> {대표 메서드의 body}
 *	3. Optional : NullPointException을 피할 수 있는 메서드의 집합 API
 *	4. Stream : 집합객체의 일련의 요소들에 대해 순차적인 접근구조를 표현할 수 있는 API
 *
 */
@WebServlet(value="/case2/imageForm.do", loadOnStartup = 1,
initParams = {@WebInitParam(name="imageFolderPath",value="D:/01.medias/image")})
public class ImageFormServlet_case2 extends HttpServlet {
	private ServletContext application; // 어떤 쓰레드가 접근하던지 똑같기 때문에 전역으로 사용
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		
		StringBuffer html = new StringBuffer();
		html.append("\n<html>");	// \n은 페이지 소스보기 편하게 하려고하는 것
		html.append("\n<head>");
		html.append("\n<meta charset='UTF-8'>");
		html.append("\n<title>Insert title here</title>");
		html.append("\n</head>");
		html.append("\n<body>");
		html.append(String.format("\n<form action='%s/image.do'>", req.getContextPath()));
		html.append("\n<select name='image'>");
		
		String imageFolderPath = getServletConfig().getInitParameter("imageFolderPath");
		File imageFolder = new File(imageFolderPath);

		File[] imageFiles = imageFolder.listFiles((d,n) ->  // 똑같은 익명함수 코드를 람다식으로 변환함
			Optional.ofNullable(application.getMimeType(n))
					.filter((m)-> m.startsWith("image/"))
					.isPresent() // mime은 있을 수도 없을 수도 있다라는 뜻(nullpointException 처리하기 위함)
		);
		
//		Stream.of(imageFiles) // for문을 대신함
//				.forEach(f -> {
//					html.append(String.format("\n<option value='%1$s'>%1$s</option>", f.getName())); // 같은 값이면 이렇게 사용해도 됨(중복사용)
//				});
		String options = Stream.of(imageFiles) // 받은 값과 리턴하는 값의 타입이 다를 때
								.map(f->String.format("<option value='%1$s'>%1$s</option>", f.getName()))
								.collect(Collectors.joining("\n"));
		
		html.append(options);
		html.append("\n</select>");
		html.append("\n<input type='submit' value='이미지 줘!!'/>");
		html.append("\n</form>");
		html.append("\n</body>");
		html.append("\n</html>");
		
		try (PrintWriter out = resp.getWriter();) {
			out.println(html);
		}
	}
}