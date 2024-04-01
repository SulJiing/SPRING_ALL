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
 * 	JSON reaponse content를 전송하는 view layer
 */
@WebServlet ("/jsonView.do")
public class JsonViewServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 서블릿의 응답(content type)을 JSON 형식으로 설정
		resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
		// Jackson ObjectMapper 객체 생성
		ObjectMapper objectMapper = new ObjectMapper()
											.registerModule(new JavaTimeModule())
											.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
											.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		// 클라이언트로 전송할 데이터를 담을 Map 객체 생성
		Map<String, Object> nativeData = new HashMap<>();
		// 현재 요청 객체의 속성들을 가져와서 Map에 담기
		Enumeration<String> attrNames = req.getAttributeNames();
		while (attrNames.hasMoreElements()) {
			String modelName = (String) attrNames.nextElement();
			Object model = req.getAttribute(modelName);
			nativeData.put(modelName,model);
		}
		
		try(
			PrintWriter out = resp.getWriter();
		){
			// Jackson ObjectMapper를 사용하여 Map 객체를 JSON으로 변환하고, 클라이언트로 전송
			objectMapper.writeValue(out, nativeData);
		}
	}
}