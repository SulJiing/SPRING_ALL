package kr.or.ddit.servlet05;

import java.util.stream.Stream;

public enum BrowserType {
	EDG("엣지"), WHALE("웨일"), CHROME("크롬"), SAFARI("사파리"), OTHER("기타");
	
	private String browserName;
	public String getBrowserName() {
		return browserName;
	}

	// 자바 1.5이전 문법
	private BrowserType(String browserName) {
		this.browserName = browserName;
	}
	
	// case4
	public static BrowserType findBrowserType(String userAgent) {
		final String agent = userAgent.toUpperCase();
		return Stream.of(values())
					.filter(c->agent.contains(c.name()))
					.findFirst()
					.orElse(OTHER);
	}
	
	public static String findBrowserName(String userAgent) {
		return findBrowserType(userAgent).getBrowserName();
	}
//	자바 1.5이전 문법
//	public static final BrowserType EDG = new BrowserType("엣지");
//	public static final BrowserType WHALE = new BrowserType("웨일");
//	public static final BrowserType CHROME = new BrowserType("크롬");
//	
//	private static BrowserType[] instances = {
//		EDG, WHALE, CHROME
//	};
//	
//	public static BrowserType[] values() {
//		return instances;
//	}
}
