package kr.or.ddit.utils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

import kr.or.ddit.common.exception.CustomPersistenceException;
import kr.or.ddit.vo.MemberVO;

public class PopulateUtils {
	// {} 코드 불록 - 생성자 다음에 실행 / static이 붙으면 처음에 딱 한번만 실행
	static {
		AbstractConverter converter = new AbstractConverter() {
			@Override
			protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
				if (value == null || value.toString().isEmpty()) return null;
				else
					// 밑에 2가지 메서드를 한번에 리턴함
					return (T) type.getDeclaredMethod("parse", CharSequence.class).invoke(null, value.toString());
//					return (T) LocalDateTime.parse(value.toString());
//					return (T) LocalDate.parse(value.toString());
			}

			@Override
			protected Class<?> getDefaultType() {
				return LocalDate.class;
			}
		};
		ConvertUtils.register(converter, LocalDate.class);
		ConvertUtils.register(converter, LocalDateTime.class);
	}

	public static <T> void populate(T bean, Map<String, ? extends Object> parameterMap) {
		try {
			BeanUtils.populate(bean, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
	}
}
