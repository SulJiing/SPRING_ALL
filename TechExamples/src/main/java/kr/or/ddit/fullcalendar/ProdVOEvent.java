package kr.or.ddit.fullcalendar;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import kr.or.ddit.vo.ProdVO;

public class ProdVOEvent extends AbstractEvent<ProdVO> {

	public ProdVOEvent(ProdVO extendedProps) {
		super(extendedProps);
	}

	@Override
	public String getId() {
		return getExtendedProps().getProdId();
	}

	@Override
	public String getGroupId() {
		return getExtendedProps().getProdLgu();
	}

	@Override
	public boolean isAllDay() {
		return true;
	}

	@Override
	public LocalDateTime getStart() {
		return LocalDateTime.of(getExtendedProps().getProdInsdate(), LocalTime.NOON);
	}

	@Override
	public LocalDateTime getEnd() {
		return getStart();
	}

	@JsonIgnore
	@Override
	public String getStartStr() {
		return null;
	}

	@JsonIgnore
	@Override
	public String getEndStr() {
		return null;
	}

	@Override
	public String getTitle() {
		return getExtendedProps().getProdName();
	}

	@Override
	public String getUrl() {
		return "/prod/prodView.do?what="+getId();
	}
	
	@Override
	public String getBackgroundColor() {
		return "green";
	}
}
