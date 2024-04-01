package kr.or.ddit.board.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.vo.AttatchVO;

@Controller
public class DownloadAndDeleteAtchController {
	@Inject
	private BoardService service;
	@Value("#{appInfo.boPath}")
	private Resource boPath;
	
	@GetMapping("/board/download/{attNo}")
	public ResponseEntity<Resource> download(@PathVariable int attNo) throws IOException {
		AttatchVO atch = service.downloadAttatch(attNo);
		String saveName = atch.getAttSavename(); 
		Resource saveFile = boPath.createRelative(saveName);
		
		if(!saveFile.exists())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s 파일을 찾을 수 없음.", atch.getAttFilename()));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentLength(atch.getAttFilesize());
//		Content-Disposition: attachment; filename="filename.jpg"
		ContentDisposition disposition = ContentDisposition.attachment()
											.filename(atch.getAttFilename(), Charset.forName("UTF-8"))
											.build();
		headers.setContentDisposition(disposition);
		return  ResponseEntity.ok()
						.headers(headers)
						.body(saveFile);
	}
	
	@DeleteMapping("/board/atch/{attNo}")
	@ResponseBody
	public Map<String, Object> deleteAttatch(@PathVariable int attNo) {
		service.removeAttatch(attNo);
		return Collections.singletonMap("success", true);
	}
}
















