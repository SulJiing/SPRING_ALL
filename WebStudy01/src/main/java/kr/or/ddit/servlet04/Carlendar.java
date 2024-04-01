package kr.or.ddit.servlet04;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/carlendar.do")
public class Carlendar extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		LocalDate now = LocalDate.now();
		int lastDayOfMonth = now.lengthOfMonth();

		int year = now.getYear();

		int month = now.getMonthValue(); // 숫자 월

		DayOfWeek day = now.getDayOfWeek(); // 요일

		int Week = now.getDayOfWeek().getValue(); // 요일(월1)

		int dayOfWeekNumber = day.getValue();

		System.out.println(now);

		req.setAttribute("now", now);
		req.setAttribute("year", year);
		req.setAttribute("month", month);
		req.setAttribute("Week", Week);
		req.setAttribute("dayOfWeekNumber", dayOfWeekNumber);
		req.setAttribute("day", day);
		
		String view = "WEB-INF/views/03/calendar.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
