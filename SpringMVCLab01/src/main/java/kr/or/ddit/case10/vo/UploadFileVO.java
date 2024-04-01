package kr.or.ddit.case10.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadFileVO {
	private String uploader;
	@NotNull
	private Integer count;
	private MultipartFile[] uploadFile;
	
	public void setUploadFile(MultipartFile[] uploadFile) {
		this.uploadFile = Arrays.stream(uploadFile)
								.filter(s->!s.isEmpty())
								.toArray(MultipartFile[]::new);
		
		/*List<MultipartFile> temp = new ArrayList<>();
		for(MultipartFile single : uploadFile) {
			if(single.isEmpty()) continue;
			temp.add(single);
		}
		MultipartFile[] tempArray = new MultipartFile[temp.size()];
		this.uploadFile = temp.toArray(tempArray);*/
	}
}
