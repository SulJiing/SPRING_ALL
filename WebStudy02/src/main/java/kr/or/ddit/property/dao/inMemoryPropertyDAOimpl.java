package kr.or.ddit.property.dao;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import kr.or.ddit.vo.PropertyVO;

/**
 *	.properties 파일을 출처로 하는 dao 구현체 
 */
public class inMemoryPropertyDAOimpl implements PropertyDAO {
	private Properties properties;

	public inMemoryPropertyDAOimpl(String logicalPath) {
		super();
		URL realPath = this.getClass().getResource(logicalPath);
		try {
			File file = new File(realPath.toURI());
			properties = new Properties();
			try(
				FileInputStream fis = new FileInputStream(file);
			){
				properties.load(fis);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertProperty(PropertyVO newProp) {
		properties.setProperty(newProp.getPropertyName(), newProp.getPropertyValue());
		return 1;
	}

	@Override
	public List<PropertyVO> selectProperties() {
		// properties 맵의 각 키-값 쌍에 대한 스트림을 생성
		return properties.entrySet().stream()
				// map() 메서드를 사용하여 각 Map.Entry를 PropertyVO로 변환
				.map(en -> {
			PropertyVO vo = new PropertyVO();
			// PropertyVO의 속성을 설정합니다. 키와 값은 각각 문자열로 변환
			vo.setPropertyName(en.getKey().toString());
			vo.setPropertyValue(en.getValue().toString());
			return vo;
			// collect() 메서드를 사용하여 스트림의 결과를 리스트로 수집
		}).collect(Collectors.toList());
	}

	@Override
	public PropertyVO selectProperty(String propertyname) {
		return Optional.ofNullable(properties.getProperty(propertyname))
				.map(pv -> {
			PropertyVO vo = new PropertyVO();
			vo.setPropertyName(propertyname);
			vo.setPropertyValue(pv);
			return vo;
		}).orElse(null);
	}

	@Override
	public int updateProperty(PropertyVO modifyProp) {
		if(properties.containsKey(modifyProp.getPropertyName())) {
			properties.setProperty(modifyProp.getPropertyName(), modifyProp.getPropertyValue());
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteProperty(String propertyName) {
		return properties.remove(propertyName) != null ? 1 : 0;
	}
}