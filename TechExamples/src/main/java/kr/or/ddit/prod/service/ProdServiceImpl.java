package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.exception.PKNotFoundException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdServiceImpl implements ProdService {
	
	private final ProdDAO dao;
	
	@Value("#{appInfo.prodPath}")
	private Resource prodFolder;

	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
		saveFolder = prodFolder.getFile();
	}
	
	/**
	 * 상품 등록이나 수정시 업로드된 상품 이미지 2진 데이터 저장
	 */
	private void processProdImage(ProdVO prod) {
		MultipartFile prodImage = prod.getProdImage();
		if(prodImage==null) return;
//		if(1==1) throw new RuntimeException("강제 발생 예외");
		try {
//			Resource imageFile = prodFolder.createRelative(prod.getProdImg());
			File imageFile = new File(saveFolder, prod.getProdImg());
			prodImage.transferTo(imageFile);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		
		ServiceResult result = dao.insertProd(prod) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		if(result == ServiceResult.OK)
			processProdImage(prod);
		
		return result;
	}

	@Override
	public List<ProdVO> retrieveProdList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectProdList(paging);
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = dao.selectProd(prodId);
		if(prod==null)
			throw new PKNotFoundException(String.format("%s 에 해당하는 상품 없음.", prodId));
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ServiceResult result =  dao.updateProd(prod) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		if(result == ServiceResult.OK)
			processProdImage(prod);
		return result;
	}

}











