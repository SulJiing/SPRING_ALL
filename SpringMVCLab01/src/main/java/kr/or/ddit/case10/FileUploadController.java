package kr.or.ddit.case10;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.case10.vo.UploadFileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case10/upload2")
public class FileUploadController {
	
	@Value("file:d:/saveFiles/")
	private Resource saveFolder;
	
	@PostConstruct // 생성이 된 이후
	public void init() {
		log.info("saveFolder : {}",saveFolder);
	}
	
	@GetMapping
	public String formUI(@ModelAttribute("fileVO") UploadFileVO fileVO) {
		return "case10/uploadForm2";
	}
	
	// 지양할 것
	public String postHandler1(HttpServletRequest req) throws IOException, ServletException {
		log.info("uploader : {}",req.getParameter("uploader"));
		log.info("count : {}",req.getParameter("count"));
		log.info("uploadFile : {}",req.getPart("uploadFile"));
		return "jsonView";
	}
	
//	@PostMapping
	public String postHandler2(@RequestParam(required = false) String uploader, 
			@RequestParam(required = false, defaultValue = "1") int count,
			@RequestPart(required = true) MultipartFile[] uploadFile) throws IOException {
		
		log.info("uploader : {}",uploader);
		log.info("count : {}",count);
		log.info("uploadFile : {}",uploadFile);
		
		for(MultipartFile single : uploadFile) {
			// 파일을 한개만 업로드해도 다른 하나는 저장하지 않게 만들기 위함
			if(single.isEmpty()) continue;
			String saveName = saveToResource(single, saveFolder);
			log.info("original file name : {}, save name : {}",single,saveName);
		}
		return "jsonView";
	}
	
	@PostMapping
	// commandObject 형식
	public String postHandler3(
			@Valid @ModelAttribute("fileVO") UploadFileVO commandObject
			, Errors errors
			) throws IOException {
		if(errors.hasErrors()) {
			return "case10/uploadForm2";
		} else {
			log.info("command object : {}",commandObject);
			log.info("uploadFile : {}",commandObject.getUploadFile());
			
			for(MultipartFile single : commandObject.getUploadFile()) {
				// 파일을 한개만 업로드해도 다른 하나는 저장하지 않게 만들기 위함
				if(single.isEmpty()) continue;
				String saveName = saveToResource(single, saveFolder);
				log.info("original file name : {}, save name : {}",single,saveName);
			}
			return "jsonView";
		}
	}
	
	private String saveToResource(MultipartFile single, Resource saveFolder) throws IOException {
		// 저장 절차
		
		// 랜덤하고 유니크한 이름을 만듦
		String saveName = UUID.randomUUID().toString();
		// 파일 저장(임의의 이름으로)을 위한 Resource 객체를 생성 - 경로를 완성해줌 (file:d:/saveFiles/파일명)
		Resource saveFileRes = saveFolder.createRelative(saveName);
		// Resource 객체로부터 실제 파일을 얻어옴
		File saveFile =	saveFileRes.getFile();
		try(
			// 업로드된 파일로부터 InputStream을 얻어옴
			InputStream is = single.getInputStream();
//			FileOutputStream fos = new FileOutputStream(saveFile);
		){
//			IOUtils.copy(is, fos);
			// InputStream을 사용하여 파일을 저장
			FileUtils.copyInputStreamToFile(is, saveFile);
			return saveName;
		}
	}
}
