package kr.or.ddit.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class MessageVO {
	private int xcNo;
	private int xcSender;
	private String xcReceiver;
	private String xcTitle;
	private String xcContent;
	private LocalDateTime xcDate;
}
