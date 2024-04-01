package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *	
 */
//@WebServlet(value="/case3/imageForm.do", loadOnStartup = 1,
//initParams = {@WebInitParam(name="imageFolderPath",value="D:/01.medias/image")})
@WebServlet("/case3/imageForm.do")
public class ImageFormServlet_case4 extends HttpServlet {
	private ServletContext application; // 어떤 쓰레드가 접근하던지 똑같기 때문에 전역으로 사용

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		
		String imageFolderPath = application.getInitParameter("imageFolderPath");
		File imageFolder = new File(imageFolderPath);

		File[] imageFiles = imageFolder.listFiles((d,n) ->  // 똑같은 익명함수 코드를 람다식으로 변환함
			Optional.ofNullable(application.getMimeType(n))
					.filter((m)-> m.startsWith("image/"))
					.isPresent() // mime은 있을 수도 없을 수도 있다라는 뜻(nullpointException 처리하기 위함)
		);
		
		String cPath = req.getContextPath();
		String options = Stream.of(imageFiles) // 받은 값과 리턴하는 값의 타입이 다를 때
								.map(f->String.format("<option value='%1$s'>%1$s</option>", f.getName()))
								.collect(Collectors.joining("\n"));
		
		req.setAttribute("cPath", cPath);
		req.setAttribute("options", options);
		
//		req.getRequestDispatcher("/01/imageForm.c41").forward(req, resp);
		req.getRequestDispatcher("/01/imageFormJQ.c41").forward(req, resp);
	}
}