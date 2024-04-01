package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

/**
 * .properties 파일이나, Database_properties 뷰를 통해 관리되고 있는, 
 * property 한건의 정보를 캡슐화할 VO(Java Bean)
 *
 */
//@Getter
//@Setter
//@ToString
@EqualsAndHashCode(of = "propertyName")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyVO implements Serializable{
	
	public PropertyVO(String propertyName) {
		super();
		this.propertyName = propertyName;
	}
	private String propertyName;
	private String propertyValue;
	private String description;
	@Exclude
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(shape = Shape.STRING)
	private LocalDate propDate;
	@Exclude
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@JsonFormat(shape = Shape.STRING)
	private LocalDateTime propTimestamp;
}
