package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/imageForm.do", loadOnStartup = 1,
initParams = {@WebInitParam(name="imageFolderPath",value="D:/01.medias/image")})
public class ImageFormServlet_case1 extends HttpServlet {
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

		File[] imageFiles = imageFolder.listFiles(new FilenameFilter() { // 이미지 파일만 찾기
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);	// "image/jpeg";
				return mime != null && mime.startsWith("image/");
			}
		});
		
		for (File tmp : imageFiles) {
			html.append(String.format("\n<option value='%1$s'>%1$s</option>", tmp.getName())); // 같은 값이면 이렇게 사용해도 됨(중복사용)
		}
		
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