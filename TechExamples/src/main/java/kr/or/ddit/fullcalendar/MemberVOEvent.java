package kr.or.ddit.fullcalendar;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.vo.MemberVO;

public class MemberVOEvent extends AbstractEvent<MemberVO> {

	public MemberVOEvent(MemberVO extendedProps) {
		super(extendedProps);
	}

	@Override
	public String getId() {
		return getExtendedProps().getMemId();
	}

	@JsonIgnore
	@Override
	public String getGroupId() {
		return null;
	}

	@Override
	public boolean isAllDay() {
		return false;
	}

	@Override
	public LocalDateTime getStart() {
		return getExtendedProps().getMemBir();
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
		return getExtendedProps().getMemName();
	}

	@Override
	public String getBackgroundColor() {
		return "yellow";
	}
}
