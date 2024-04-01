package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/image.do") // 싱글벨류 어노테이션(single value annotation)
//@WebServlet(loadOnStartup = 1) // 멀티벨류 어노테이션(multi value annotation)
@WebServlet(value="/image.do",loadOnStartup = 1, 
initParams = {@WebInitParam(name = "imageFolderPath",value="D:/01.medias/image")})
public class ImageStreamingServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
//		config.getInitParameter("imageFolderPath");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imageFolderPath = getServletConfig().getInitParameter("imageFolderPath"); // "D:/01.medias/image";
		File imageFolder = new File(imageFolderPath);
		
		String imageName = req.getParameter("image");	//"훈발럼.jpg";
		
		if(imageName == null || imageName.trim().isEmpty()) {  // null이거나, 공백 확인
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"필수 파라미터 누락");
			return;
		}
		
		File imageFile = new File(imageFolder, imageName);
		if(!imageFile.exists()) {	// 잘못된 이름
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,"그런 이미지 없음");
			return;
		}
		
		ServletContext application = getServletContext();
		
		String mime = application.getMimeType(imageName);	// "image/jpeg";
		resp.setContentType(mime);
		resp.setContentLengthLong(imageFile.length());
		
		/*FileInputStream fis = null;
		try {
			fis = new FileInputStream(imageFile);
		} finally {
			if(fis != null)
			fis.close();
		}*/
		
//		try with resource 구문의 형태(java 1.7버전 이후 문법) - close가 알아서 됨
//		try( Closable 객체 생성 구문) {}catch(e) {}finally {}
		try(
			FileInputStream fis = new FileInputStream(imageFile);	
			OutputStream os = resp.getOutputStream();
		){
			int buffer = -1;
			while((buffer=fis.read()) !=-1) { // EOF(End Of File, -1, null) 문자 이전까지 반복
				os.write(buffer);
			}
			os.flush(); // 남은 데이터 방출
		}
	}
}