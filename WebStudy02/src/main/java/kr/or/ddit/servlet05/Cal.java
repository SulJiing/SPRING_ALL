package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.MediaType;

@WebServlet("/calculate.do")
public class Cal extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String leftOpParam = request.getParameter("leftOp");
		int leftOp = (leftOpParam != null && !leftOpParam.isEmpty()) ? Integer.parseInt(leftOpParam) : 0;
		String rightOpParam = request.getParameter("rightOp");
		int rightOp = (rightOpParam != null && !rightOpParam.isEmpty()) ? Integer.parseInt(rightOpParam) : 0;
		String operator = request.getParameter("operator");
		
//		System.out.println(leftOp+""+rightOp);
//		System.out.println(operator);
		
		int result = 0;

        switch (operator) {
            case "PLUS":
                result = MediaType.PLUS(leftOp, rightOp);
                break;
            case "MINUS":
            	result = MediaType.MINUS(leftOp, rightOp);
            	break;
            case "MULTIPLY":
            	result = MediaType.MULTIPLY(leftOp, rightOp);
            	break;
            case "DIVIDE":
            	result = MediaType.DIVIDE(leftOp, rightOp);
            	break;
            default:
                // 예외 처리 등을 수행
                break;
        }
        
        PrintWriter out = response.getWriter();
        
        String mimeType = request.getParameter("settings.dataType");
        System.out.println(mimeType);
        if (mimeType != null) {
            switch (mimeType) {
                case "json":
                    response.setContentType(MediaType.APPLICATION_JSON.getMimeType());
                    out.print(result);
                    break;
                case "xml":
                    response.setContentType(MediaType.APPLICATION_XML.getMimeType());
                    out.print(result);
                    break;
                case "html":
                default:
                    response.setContentType(MediaType.TEXT_HTML.getMimeType());
                    out.print(result);
                    break;
            }
        } else {
            // Handle the case where mimeType is null
            response.setContentType(MediaType.TEXT_HTML.getMimeType());
            out.print("Error: dataType parameter is null");
        }

//		out.print(result);
//		request.setAttribute("result", out);
	}
}
