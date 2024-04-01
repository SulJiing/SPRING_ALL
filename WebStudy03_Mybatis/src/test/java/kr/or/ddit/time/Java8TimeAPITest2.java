package kr.or.ddit.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 순서
class Java8TimeAPITest2 {

	@Test
	@Order(2)
	void test1() {
		System.out.println(LocalDateTime.now());
		// 다른 지역의 시간대 구글에 TimeZone -> Oracle
		System.out.println(LocalDateTime.now(ZoneId.of("America/Anchorage")));
		// from 특정 시간 객체에 기준
		System.out.println(LocalDate.from(LocalDateTime.now(ZoneId.of("America/Anchorage"))));
		System.out.println(YearMonth.from(LocalDate.from(LocalDateTime.now(ZoneId.of("America/Anchorage")))));
		System.out.println(Year.from(YearMonth.from(LocalDate.from(LocalDateTime.now(ZoneId.of("America/Anchorage"))))));
		
	}

	@Test
	@Order(1)
	void test2() {
		LocalDateTime current = LocalDateTime.now();
		// 현재 시간을 기준으로 날짜를 만들고 날짜의 '-'등 스타일을 바꾸고 지역을 바꿔서 언어를 바꿀 수 있다.
		System.out.println(LocalDate.from(current).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.FRENCH)));
		System.out.println(YearMonth.from(current));
		System.out.println(Year.from(current));
	}
	
	@Test
	void test3() {
		System.out.println(YearMonth.of(2023, 1).format(DateTimeFormatter.ofPattern("yyyy, MMMM").withLocale(Locale.ENGLISH)));
		
	}
	
	@Test
	void test4() {
		System.out.println(YearMonth.now().minusMonths(1));
		System.out.println(YearMonth.now().plusMonths(1));
	}
	
	@Test
	void test5() {
		LocalDate today = LocalDate.now();
		DayOfWeek dayOfWeek = today.getDayOfWeek();
		// locale객체로부터 locale태그를 받아오는 것 (ko-KR)
		Locale locale = Locale.forLanguageTag(Locale.KOREAN.toLanguageTag());
		System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, locale));
		
		Stream.of(Locale.getAvailableLocales()) //전세계언어
			.forEach(l->{
				System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, l));
			});
		
		for(Locale i : Locale.getAvailableLocales()) {
			System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, i));
		}
	}
	
	@Test
	void test6() {
		// 독일은 일요일부터 시작인데 월요일부터 나옴 -> 배열이 잘못됨
		Arrays.stream(DayOfWeek.values()).forEach(dow->{
			System.out.println(dow.getDisplayName(TextStyle.FULL, Locale.GERMAN));
		});
	}
	
	@Test
	void test7() {
		Locale locale = Locale.KOREAN;
		WeekFields weekFields = WeekFields.of(locale);
		DayOfWeek first = weekFields.getFirstDayOfWeek();
		for(int i=0; i<7; i++) {
			DayOfWeek tmp = first.plus(i);
			System.out.println(tmp.getDisplayName(TextStyle.FULL, locale));
		}
	}
}