package kr.or.ddit.servlet06;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.CalendarVO;

/**
 *	Model2 + MVC 
 *	servlet(Model+Controller : request 처리자)
 *		1. 요청을 받고,	 - @WebServlet(web.xml servlet-mapping)	(식당에 손님이 들어옴)
 *		2. 요청을 분석하고, 검증. - (request line, header, body)...	(손님이 메뉴에 있는 음식 주문했는지)
 *		3. Model 생성 (주문한 음식 조리)
 *		4. Modle을 전달 및 공유	- setAttribute(name,value) - 대상은 jsp파일
 *		5. view layer 선택 (접시 고르기)
 *		6. 제어의 이동(controller -> view) - forward	(접시에 초밥 올리기)
 *	jsp (View : response 처리자)
 *		1. Model확보 - getAttribute(name)
 *		2. model을 content로...
 */
@WebServlet ("/case3/calendar.do")
public class CalendarServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view ="/WEB-INF/views/06/case3/calendarForm.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	 	Locale locale = request.getLocale(); // 우선으로 설정된 지역을 사용하는 것
//	 	Locale locale = Locale.CANADA_FRENCH; // 캐나다로 바꾼 것
		Locale locale = Optional.ofNullable(req.getParameter("locale")) // 지역으로 언어바꾸기
				.filter(lp->!lp.trim().isEmpty())
				.map(lp->Locale.forLanguageTag(lp))
				.orElse(req.getLocale()); // 없으면 클라인언트 기준 언어로
		
//	 	Locale locale = Locale.getDefault(); // 서버의 지역을 사용하는 것
		ZoneId zone = ZoneId.systemDefault(); // 시스템의 기본 시간대
		
//	 	LocalDateTime current = LocalDateTime.now(zone);
		ZonedDateTime current = ZonedDateTime.now(zone); // 지정된 시간대 가져오기
		FormatStyle fullStyle = FormatStyle.FULL; // 형식 지정
		
//	 	YearMonth thisMonth = YearMonth.from(current); // 현재 월 가져오기 근데 밑에서 쓰기 때문에 필요가 없어짐
		
		int targetYear = Optional.ofNullable(req.getParameter("year")) // 파라미터의 기본 시간대 잡기
				.map(yp->new Integer(yp))
				.orElse(YearMonth.from(current).getYear());
		YearMonth thisMonth = Optional.ofNullable(req.getParameter("month")) // 여기 thisMonth는 이제 무조건 현재가 아닐수도 있음
				.map(mp->YearMonth.of(targetYear, Integer.parseInt(mp)))
				.orElse(YearMonth.from(current));
		
		YearMonth beforeMonth = thisMonth.minusMonths(1); // 한 달 빼기
		YearMonth nextMonth = thisMonth.plusMonths(1); // 한 달 더하기
		
		WeekFields weekFields = WeekFields.of(locale); // 주의 첫번째 요일을 사용해 weekFields 생성
		DayOfWeek firstDOW = weekFields.getFirstDayOfWeek(); // 주의 첫번째 요일 가져오기
		LocalDate firstDate = thisMonth.atDay(1); // 이번달의 첫번째 날짜 가져오기
		int firstDateDOW = firstDate.get(weekFields.dayOfWeek()); // 이번달의 첫번째 날짜의 요일 가져오기
		int offset = firstDateDOW -1; // 앞에 들어가는 빈칸 = 전달의 날짜
		
		CalendarVO calVO = new CalendarVO();
		calVO.setBeforeMonth(beforeMonth);
		calVO.setCurrent(current);
		calVO.setFirstDate(firstDate);
		calVO.setFirstDOW(firstDOW);
		calVO.setFormatStyle(fullStyle);
		calVO.setLocale(locale);
		calVO.setNextMonth(nextMonth);
		calVO.setOffset(offset);
		calVO.setThisMonth(thisMonth);
		
		req.setAttribute("calendar", calVO);
		String view = "/WEB-INF/views/06/case3/calendar.jsp";
		req.getRequestDispatcher(view).forward(req, resp);

	}
}
