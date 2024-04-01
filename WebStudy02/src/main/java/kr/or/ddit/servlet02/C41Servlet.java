package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *	
 */
@WebServlet("*.c41")
public class C41Servlet extends HttpServlet {
	private ServletContext application; // 어떤 쓰레드가 접근하던지 똑같기 때문에 전역으로 사용

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		
		try {
			String templateSource = readTemplate(req);
			StringBuffer html = new StringBuffer();
			html.append(mergeDataAndTemplate(req,templateSource));
			
			try (PrintWriter out = resp.getWriter();) {
				out.println(html);
			}
		} catch(FileNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
		}
	}

	private String mergeDataAndTemplate(HttpServletRequest req, String templatSource) {
	      //Collection View
	      Enumeration<String> names = req.getAttributeNames();
	      while (names.hasMoreElements()) {
	         String key = (String) names.nextElement();
	         templatSource=templatSource.replace("#"+key+"#", req.getAttribute(key).toString());
	      }
	      return templatSource;
	}
	
	/**
	 * 	여기서 확장자 c41의 파일의 정보를 읽어옴
	 * @param req
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private String readTemplate(HttpServletRequest req) throws FileNotFoundException, IOException, ServletException {
		// /WebStudy01/01/sample.c41 -> /01/sample 추출
		String tmplUrl = req.getRequestURI();
		
		System.out.printf("servlet path : %s\n",req.getServletPath()); // 값을 못가져왔다고 가정 하고 getRequestURI 사용해서 정규식 공부
		System.out.printf("request URI : %s\n",req.getRequestURI());
		
		String contextPath = getServletContext()/*req*/.getContextPath();
		String regex = contextPath + "([\\w_/]+)" + "\\.c41"; // 정규식에서는 .과 /가 메타문자이기때문에 이스케이프 문자를 넣어주어야함
		Pattern regexp = Pattern.compile(regex);
		Matcher matcher = regexp.matcher(tmplUrl);
		
		if(matcher.find()) {
			String filePathPart = matcher.group(1);
			tmplUrl = filePathPart + ".c41";
			
			// d:/~~~~/01/imageForm.c41 => 절대경로
			String tmpFSPath = getServletContext().getRealPath(tmplUrl);
			File templateFile = new File(tmpFSPath);
			
			if(!templateFile.exists()) {
				throw new FileNotFoundException(String.format(" %s 그런 파일 없다..",tmplUrl));
			}
			
			try (FileReader reader = new FileReader(templateFile); // 1차 스트림(파일에 직접 연결)
					BufferedReader br = new BufferedReader(reader); // 2차 스트림(1차 스트림에 연결형(파일을 바로 열지 못함)
			) {
				String tmp = null;
				StringBuffer template = new StringBuffer();
				while ((tmp = br.readLine()) != null) { // 한글자씩이 아니라 한 행씩 읽어들임 -> 빨라짐 - 위에 BufferedReader객체가 필요함
					template.append(tmp);
					template.append("\n");
				}
				return template.toString();
			} // try end
		} else {
			throw new ServletException("정규식을 파싱해서 c41 파일의 위치를 찾는 과정에서 예외 발생");
		} // if(matcher.find()) end
	}
}