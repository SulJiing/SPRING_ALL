package kr.or.ddit.case10.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/case10/upload1")
@MultipartConfig // 한번에 업로드하는 용량,위치 지정해주는 어노테이션(정책제한)
public class FileUploadControllerServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "case10/uploadForm1";
		req.getRequestDispatcher("/WEB-INF/views/"+viewName+".jsp").forward(req, resp);
	}
	
	private File saveFolder = new File("D:/saveFiles");
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploader = req.getParameter("uploader");
		String count = req.getParameter("count");
		Part uploadFile = req.getPart("uploadFile");
		log.info("uploader : {}",uploader);
		log.info("count : {}",count);
		
		File saveFile = new File(saveFolder,uploadFile.getSubmittedFileName()); // 파일의 원본명(getName X)
		byte[] buffer = new byte[1024];
		try(
			InputStream is =  uploadFile.getInputStream();
			FileOutputStream fos = new FileOutputStream(saveFile);
		){
			int position = -1;
			while((position=is.read(buffer))!=-1) {
				fos.write(buffer,0,position);
			}
		}
		log.info("uploadFile : {}",uploadFile);
	}
}
