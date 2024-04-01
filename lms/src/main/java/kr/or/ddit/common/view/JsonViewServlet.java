package kr.or.ddit.common.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.or.ddit.enumpkg.MediaType;

/**
 * JSON response content를 전송하는 view layer
 *
 */
@WebServlet("/jsonView.do")
public class JsonViewServlet extends HttpServlet{
	
	//do계열의 콜백 사용 안하겠다. //view니까 request와는 관련이 없어야하기 때문에
	//json이필요한 어떠한 것이라도 이 공통 view로 처리하면 됨
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType(MediaType.APPLICATION_JSON_VLAUE);
		
		ObjectMapper objectMapper = new ObjectMapper()
										.registerModule(new JavaTimeModule())
										.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
										.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		Map<String, Object> nativeData = new HashMap<String, Object>();
		Enumeration<String> attrNames = req.getAttributeNames();
		while (attrNames.hasMoreElements()) {
			String modelName = (String) attrNames.nextElement();
			Object model = req.getAttribute(modelName);
			nativeData.put(modelName, model);
		}
		
		try(
			PrintWriter out = resp.getWriter();
		){
								// 직렬화 마셜링
			objectMapper.writeValue(out, nativeData);
		}
		
	}
	
	
	
	
	
	
}
